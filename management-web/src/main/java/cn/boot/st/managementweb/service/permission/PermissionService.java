package cn.boot.st.managementweb.service.permission;

import cn.boot.st.managementweb.dataobject.dto.PermissionAssignAdminRoleDTO;
import cn.boot.st.managementweb.dataobject.dto.PermissionAssignRoleResourceDTO;

import java.util.Set;

/**
 * @Classname PermissionService
 * @Description
 * @Date 2020/12/5
 * @Created by maht
 */
public interface PermissionService {

    Set<Integer> listRoleResources(Integer roleId);

    /**
     * 给角色赋予资源
     *
     * @param permissionAssignRoleResourceDTO
     */
    void assignRoleResource(PermissionAssignRoleResourceDTO permissionAssignRoleResourceDTO);

    /**
     * 获得管理员拥有的角色编号列表
     *
     * @param adminId 管理员编号
     * @return 资源编号列表
     */
    Set<Integer> listAdminRoles(Integer adminId);

    void assignAdminRole(PermissionAssignAdminRoleDTO permissionAssignAdminRoleDTO);

}
