package cn.boot.st.system.service.impl;

import cn.boot.common.framework.enums.role.RoleCodeEnum;
import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.st.system.convert.ResourceConvert;
import cn.boot.st.system.dataobject.*;
import cn.boot.st.system.dto.PermissionAssignAdminRoleDTO;
import cn.boot.st.system.dto.PermissionAssignRoleResourceDTO;
import cn.boot.st.system.mapper.*;
import cn.boot.st.system.service.PermissionService;
import cn.boot.st.system.vo.ResourceVO;
import cn.boot.st.system.vo.RoleResourceVo;
import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.boot.st.system.constants.SystemCodeConstants.*;


/**
 * @Classname PermissionServiceImpl
 * @Description
 * @Date 2020/12/5
 * @Created by maht
 */
@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public Set<Integer> listRoleResources(Integer roleId) {
        // 超级管理员，拥有所有资源
        if (hasSuperAdmin(Collections.singleton(roleId))) {
            List<ResourceDO> resourceDOs = resourceMapper.selectList(null);
            ResourceConvert.INSTANCE.convertList(resourceDOs);
            List<ResourceVO> resourceVOS = ResourceConvert.INSTANCE.convertList(resourceDOs);
            return resourceVOS.stream().map(ResourceVO::getId).collect(Collectors.toSet());
        }
        List<RoleResourceDO> roleResourceDOs = roleResourceMapper.selectListByRoleId(roleId);
        return roleResourceDOs.stream().map(RoleResourceDO::getResourceId).collect(Collectors.toSet());
    }

    @Override
    public void assignRoleResource(PermissionAssignRoleResourceDTO assignRoleResourceDTO) {
        // 校验角色是否存在
        if (roleMapper.selectById(assignRoleResourceDTO.getRoleId()) == null) {
            throw ServiceExceptionUtil.exception(ROLE_NOT_EXISTS);
        }
        // 校验是否有不存在的资源
        if (!CollectionUtils.isEmpty(assignRoleResourceDTO.getResourceIds())) {
            int dbResourceSize = resourceMapper.selectCountByIdsAndType(assignRoleResourceDTO.getResourceIds(), null);
            if (assignRoleResourceDTO.getResourceIds().size() != dbResourceSize) {
                throw ServiceExceptionUtil.exception(PERMISSION_ROLE_ASSIGN_RESOURCE_NOT_EXISTS);
            }
        }
        // 删除老的分配的资源关系，然后添加新的分配的资源关系
        // 标记角色原资源关系都为删除
        roleResourceMapper.deleteByRoleId(assignRoleResourceDTO.getRoleId());
        // 创建 RoleResourceDO 数组，并插入到数据库
        if (!CollectionUtils.isEmpty(assignRoleResourceDTO.getResourceIds())) {
            List<RoleResourceDO> roleResources = assignRoleResourceDTO.getResourceIds().stream()
                    .map(resourceId -> new RoleResourceDO().setRoleId(assignRoleResourceDTO.getRoleId()).setResourceId(resourceId)).collect(Collectors.toList());
            roleResourceMapper.insertList(roleResources);
        }
    }

    @Override
    public Set<Integer> listAdminRoles(Integer adminId) {
        List<AdminRoleDO> adminRoleDOs = adminRoleMapper.selectListByAdminId(adminId);
        return adminRoleDOs.stream().map(AdminRoleDO::getRoleId).collect(Collectors.toSet());
    }

    @Override
    public void assignAdminRole(PermissionAssignAdminRoleDTO permissionAssignAdminRoleDTO) {
        // 校验账号存在
        AdminDO admin = adminMapper.selectById(permissionAssignAdminRoleDTO.getAdminId());
        if (admin == null) {
            throw ServiceExceptionUtil.exception(ADMIN_NOT_FOUND);
        }
        // 校验是否有不存在的角色
        if (!CollectionUtils.isEmpty(permissionAssignAdminRoleDTO.getRoleIds())) {
            List<RoleDO> roles = roleMapper.selectBatchIds(permissionAssignAdminRoleDTO.getRoleIds());
            if (roles.size() != permissionAssignAdminRoleDTO.getRoleIds().size()) {
                throw ServiceExceptionUtil.exception(ADMIN_ASSIGN_ROLE_NOT_EXISTS);
            }
        }
        // 删除老的分配的角色关系，然后添加新的分配的角色关系
        // 标记管理员角色源关系都为删除
        adminRoleMapper.deleteByAdminId(permissionAssignAdminRoleDTO.getAdminId());
        // 创建 RoleResourceDO 数组，并插入到数据库
        if (!CollectionUtil.isEmpty(permissionAssignAdminRoleDTO.getRoleIds())) {
            List<AdminRoleDO> adminRoleDOs = permissionAssignAdminRoleDTO.getRoleIds().stream()
                    .map(roleId -> new AdminRoleDO().setAdminId(permissionAssignAdminRoleDTO.getAdminId()).setRoleId(roleId)).collect(Collectors.toList());
            adminRoleMapper.insertList(adminRoleDOs);
        }
    }

    @Override
    public Set<Integer> selectListByPermissions(Collection<String> permissions) {
        List<ResourceDO> resourceDOS = resourceMapper.selectListByPermissions(permissions);
        Set<Integer> integerSet = resourceDOS.stream().map(ResourceDO::getId).collect(Collectors.toSet());
        return integerSet;
    }

    @Override
    public List<RoleResourceVo> selectListByResourceIds(Collection<Integer> resourceIds) {
        List<RoleResourceDO> roleResourceDOS = roleResourceMapper.selectListByResourceIds(resourceIds);
        List<RoleResourceVo> roleResourceVos = ResourceConvert.INSTANCE.convertRoleResourceVoList(roleResourceDOS);
        return roleResourceVos;
    }


    /**
     * 判断角色是否有超级管理员
     *
     * @param roleIds 角色编号列表
     * @return 是否有超级管理员
     */
    public boolean hasSuperAdmin(Collection<Integer> roleIds) {
        List<RoleDO> roleDOs = roleMapper.selectBatchIds(roleIds);
        for (RoleDO roleDO : roleDOs) {
            if (RoleCodeEnum.SUPER_ADMIN.getCode().equals(roleDO.getCode())) {
                return true;
            }
        }
        return false;
    }
}
