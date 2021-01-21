package cn.boot.st.system.service.impl;

import cn.boot.common.framework.enums.role.RoleTypeEnum;
import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.system.convert.RoleConvert;
import cn.boot.st.system.dataobject.AdminRoleDO;
import cn.boot.st.system.dataobject.RoleDO;
import cn.boot.st.system.dto.RoleCreateDTO;
import cn.boot.st.system.dto.RolePageDTO;
import cn.boot.st.system.dto.RoleUpdateDTO;
import cn.boot.st.system.mapper.AdminRoleMapper;
import cn.boot.st.system.mapper.RoleMapper;
import cn.boot.st.system.mapper.RoleResourceMapper;
import cn.boot.st.system.service.RoleService;
import cn.boot.st.system.vo.RoleVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static cn.boot.st.system.constants.SystemCodeConstants.*;


/**
 * @Classname RoleServiceImpl
 * @Description
 * @Date 2020/12/5 18:00
 * @Created by maht
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    public Integer createRole(RoleCreateDTO roleCreateDTO, Integer createAdminId) {
        checkDuplicateRole(roleCreateDTO.getName(), roleCreateDTO.getCode(), null);
        // 插入到数据库
        RoleDO roleDO = RoleConvert.INSTANCE.convert(roleCreateDTO).setCreateAdminId(createAdminId);
        roleDO.setType(RoleTypeEnum.CUSTOM.getType());
        roleMapper.insert(roleDO);
        return roleDO.getId();
    }

    @Override
    public void updateRole(@Valid RoleUpdateDTO updateDTO) {
        // 校验更新的角色是否存在
        RoleDO roleDO = roleMapper.selectById(updateDTO.getId());
        if (roleMapper.selectById(updateDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(ROLE_NOT_EXISTS);
        }
        // 内置角色，不允许修改
        if (RoleTypeEnum.SYSTEM.getType().equals(roleDO.getType())) {
            throw ServiceExceptionUtil.exception(ROLE_CAN_NOT_UPDATE_SYSTEM_TYPE_ROLE);
        }
        // 校验角色的唯一字段是否重复
        checkDuplicateRole(updateDTO.getName(), updateDTO.getCode(), updateDTO.getId());
        // 更新到数据库

        RoleDO updateObject = RoleConvert.INSTANCE.convert(updateDTO);
        roleMapper.updateById(updateObject);
    }

    @Override
    public void deleteRole(Integer roleId) {
        // 校验删除的角色是否存在
        RoleDO roleDO = roleMapper.selectById(roleId);
        if (roleMapper.selectById(roleId) == null) {
            throw ServiceExceptionUtil.exception(ROLE_NOT_EXISTS);
        }
        // 内置角色，不允许删除
        if (RoleTypeEnum.SYSTEM.getType().equals(roleDO.getType())) {
            throw ServiceExceptionUtil.exception(ROLE_CAN_NOT_DELETE_SYSTEM_TYPE_ROLE);
        }
        // 标记删除
        roleMapper.deleteById(roleId);
        // 标记删除 RoleResource
        roleResourceMapper.deleteByRoleId(roleId);
        // 标记删除 AdminRole
        adminRoleMapper.deleteByRoleId(roleId);
    }

    @Override
    public RoleVO getRole(Integer roleId) {

        RoleDO roleDO = roleMapper.selectById(roleId);
        return RoleConvert.INSTANCE.convert(roleDO);
    }

    @Override
    public List<RoleVO> listAllRoles() {
        List<RoleDO> roleDOs = roleMapper.selectList(null);
        return RoleConvert.INSTANCE.convertList(roleDOs);


    }

    @Override
    public List<RoleVO> listRoles(List<Integer> roleIds) {
        List<RoleDO> roleDOs = roleMapper.selectBatchIds(roleIds);
        return RoleConvert.INSTANCE.convertList(roleDOs);
    }

    @Override
    public PageResult<RoleVO> pageRole(RolePageDTO rolePageDTO) {
        IPage<RoleDO> roleDOIPage = roleMapper.selectPage(rolePageDTO);
        PageResult<RoleVO> roleVOPageResult = RoleConvert.INSTANCE.convertPage(roleDOIPage);
        return roleVOPageResult;
    }

    @Override
    public List<Integer> getUserRoleInfo(Integer userId) {
        List<AdminRoleDO> adminRoleDOS = adminRoleMapper.selectListByAdminId(userId);
        return adminRoleDOS.stream().map(AdminRoleDO::getRoleId).collect(Collectors.toList());
    }

    /**
     * 校验角色的唯一字段是否重复
     * <p>
     * 1. 是否存在相同名字的角色
     * 2. 是否存在相同编码的角色
     *
     * @param name 角色名字
     * @param code 角色额编码
     * @param id   角色编号
     */
    private void checkDuplicateRole(String name, String code, Integer id) {
        // 1. 该 name 名字被其它角色所使用
        RoleDO role = roleMapper.selectByName(name);
        if (role != null && !role.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(ROLE_NAME_DUPLICATE, name);
        }
        // 2. 是否存在相同编码的角色
        if (!StringUtils.hasText(code)) {
            return;
        }
        // 该 code 编码被其它角色所使用
        role = roleMapper.selectByCode(code);
        if (role != null && !role.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(ROLE_CODE_DUPLICATE, name);
        }
    }

}
