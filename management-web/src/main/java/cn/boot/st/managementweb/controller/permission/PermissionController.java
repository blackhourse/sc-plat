package cn.boot.st.managementweb.controller.permission;

/**
 * @Classname PermissionController
 * @Description
 * @Date 2020/12/5
 * @Created by maht
 */

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.managementweb.dataobject.dto.PermissionAssignAdminRoleDTO;
import cn.boot.st.managementweb.dataobject.dto.PermissionAssignRoleResourceDTO;
import cn.boot.st.managementweb.service.permission.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static cn.boot.common.framework.vo.CommonResult.success;

/**
 * 权限 Controller
 */
@RestController
@RequestMapping("/permission")
@Api(tags = "权限")
@Validated
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/list-role-resources")
    @ApiOperation("获得角色拥有的资源编号")
    @ApiImplicitParam(name = "roleId", value = "角色编号", required = true)
    public CommonResult<Set<Integer>> listRoleResources(Integer roleId) {
        return success(permissionService.listRoleResources(roleId));
    }


    @PostMapping("/assign-role-resource")
    @ApiOperation("赋予角色资源")
    public CommonResult<Boolean> assignRoleResource(PermissionAssignRoleResourceDTO assignRoleResourceDTO) {
        permissionService.assignRoleResource(assignRoleResourceDTO);
        return success(true);
    }

    @GetMapping("/list-admin-roles")
    @ApiOperation("获得管理员拥有的角色编号列表")
    @ApiImplicitParam(name = "adminId", value = "管理员编号", required = true)
    public CommonResult<Set<Integer>> listAdminRoles(Integer adminId) {
        return success(permissionService.listAdminRoles(adminId));
    }

    @PostMapping("/assign-admin-role")
    @ApiOperation("赋予用户角色")
    public CommonResult<Boolean> assignAdminRole(PermissionAssignAdminRoleDTO assignAdminRoleDTO) {
        permissionService.assignAdminRole(assignAdminRoleDTO);
        return success(true);
    }


}