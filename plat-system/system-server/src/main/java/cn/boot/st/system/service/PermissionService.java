package cn.boot.st.system.service;

import cn.boot.st.system.dto.PermissionAssignAdminRoleDTO;
import cn.boot.st.system.dto.PermissionAssignRoleResourceDTO;
import cn.boot.st.system.vo.RoleResourceVo;

import java.util.Collection;
import java.util.List;
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

    /**
     * 赋予用户角色
     *
     * @param permissionAssignAdminRoleDTO
     */
    void assignAdminRole(PermissionAssignAdminRoleDTO permissionAssignAdminRoleDTO);

    /**
     * 查询权限对应资源
     *
     * @param permissions
     * @return
     */
    Set<Integer> selectListByPermissions(Collection<String> permissions);

    List<RoleResourceVo> selectListByResourceIds(Collection<Integer> resourceIds);


}
