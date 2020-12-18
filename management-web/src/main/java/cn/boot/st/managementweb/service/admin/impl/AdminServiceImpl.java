package cn.boot.st.managementweb.service.admin.impl;

import cn.boot.common.framework.enums.CommonStatusEnum;
import cn.boot.common.framework.enums.admin.AdminStatusEnum;
import cn.boot.common.framework.enums.admin.AdminUsernameEnum;
import cn.boot.common.framework.enums.role.RoleCodeEnum;
import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.common.framework.util.DigestUtils;
import cn.boot.st.managementweb.convert.admin.AdminConvert;
import cn.boot.st.managementweb.dataobject.domain.AdminDO;
import cn.boot.st.managementweb.dataobject.domain.RoleDO;
import cn.boot.st.managementweb.controller.admin.dto.AdminCreateDTO;
import cn.boot.st.managementweb.controller.admin.dto.AdminUpdateInfoDTO;
import cn.boot.st.managementweb.controller.admin.dto.AdminUpdateStatusDTO;
import cn.boot.st.managementweb.mapper.admin.AdminMapper;
import cn.boot.st.managementweb.mapper.admin.DepartmentMapper;
import cn.boot.st.managementweb.mapper.role.RoleMapper;
import cn.boot.st.managementweb.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

import static cn.boot.common.framework.constant.SystemErrorCodeConstants.*;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public AdminDO getById(Integer id) {
        departmentMapper.selectById(1);

        return adminMapper.selectById(id);
    }

    @Override
    public Integer createAdmin(AdminCreateDTO createDTO) {
        // 校验账号唯一
        if (adminMapper.selectByUsername(createDTO.getUsername()) != null) {
            throw ServiceExceptionUtil.exception(ADMIN_USERNAME_EXISTS);
        }

        // 加密密码
        String passwordSalt = genPasswordSalt();
        String password = encodePassword(createDTO.getPassword(), passwordSalt);
        // 保存到数据库
        AdminDO admin = AdminConvert.INSTANCE.convert(createDTO)
                .setPassword(password).setPasswordSalt(passwordSalt)
                .setStatus(CommonStatusEnum.ENABLE.getValue());
        adminMapper.insert(admin);
        return admin.getId();
    }

    @Override
    public void updateAdmin(AdminUpdateInfoDTO adminUpdateInfoDTO) {


        AdminDO adminDO = adminMapper.selectById(adminUpdateInfoDTO.getId());
        if (adminDO == null) {
            throw ServiceExceptionUtil.exception(ADMIN_NOT_FOUND);
        }
        // 校验是否为特殊账号，不允许编辑
        if (AdminUsernameEnum.ADMIN.getUsername().equals(adminDO.getUsername())
                || AdminUsernameEnum.DEMO.getUsername().equals(adminDO.getUsername())) {
            throw ServiceExceptionUtil.exception(ADMIN_ADMIN_CAN_NOT_UPDATE);
        }
        //检车账号是否唯一
        if (StringUtils.hasText(adminUpdateInfoDTO.getUsername())) {
            AdminDO selectByUsername = adminMapper.selectByUsername(adminUpdateInfoDTO.getUsername());
            if (selectByUsername != null && !selectByUsername.getId().equals(adminUpdateInfoDTO.getId())) {
                throw ServiceExceptionUtil.exception(ADMIN_USERNAME_EXISTS);
            }
        }
        // 如果有更新状态，则校验是否已经是该状态
        if (adminUpdateInfoDTO.getStatus() != null && adminUpdateInfoDTO.getStatus().equals(adminDO.getStatus())) {
            throw ServiceExceptionUtil.exception(ADMIN_STATUS_EQUALS);
        }
        // 更新到数据库
        AdminDO updateAdmin = AdminConvert.INSTANCE.convert(adminUpdateInfoDTO);

        // 判断密码是否需要更新，如果需要更新的话，需要加密
        boolean hasText = StringUtils.hasText(adminUpdateInfoDTO.getPassword());
        if (hasText) {
            String genPasswordSalt = genPasswordSalt();
            String password = encodePassword(adminUpdateInfoDTO.getPassword(), genPasswordSalt);
            updateAdmin.setPassword(password).setPasswordSalt(genPasswordSalt);
        }
        adminMapper.updateById(updateAdmin);
        // 如果修改密码 或者禁用管理员
        if (hasText || AdminStatusEnum.INACTIVE.getStatus().equals(adminUpdateInfoDTO.getStatus())) {
            // todo remove token
        }
    }

    @Override
    public void updateAdminStatus(AdminUpdateStatusDTO adminUpdateStatusDTO) {
        AdminUpdateInfoDTO adminUpdateInfoDTO = AdminConvert.INSTANCE.convert(adminUpdateStatusDTO);
        this.updateAdmin(adminUpdateInfoDTO);
    }

    @Override
    public Boolean hasSuperAdmin(Collection<Integer> roleIds) {

        List<RoleDO> roleDOs = roleMapper.selectBatchIds(roleIds);
        for (RoleDO roleDO : roleDOs) {
            if (RoleCodeEnum.SUPER_ADMIN.getCode().equals(roleDO.getCode())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    private String genPasswordSalt() {
        return DigestUtils.genBcryptSalt();
    }

    private String encodePassword(String password, String salt) {
        return DigestUtils.bcrypt(password, salt);
    }


}
