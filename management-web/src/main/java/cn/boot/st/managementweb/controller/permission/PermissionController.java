package cn.boot.st.managementweb.controller.permission;

/**
 * @Classname PermissionController
 * @Description
 * @Date 2020/12/5
 * @Created by maht
 */

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.managementweb.controller.permission.dto.PermissionAssignAdminRoleDTO;
import cn.boot.st.managementweb.controller.permission.dto.PermissionAssignRoleResourceDTO;
import cn.boot.st.managementweb.controller.permission.vo.RoleResourceVo;
import cn.boot.st.managementweb.service.permission.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    private Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/list-role-resources")
    @ApiOperation("获得角色拥有的资源编号")
    @ApiImplicitParam(name = "roleId", value = "角色编号", required = true)
    public CommonResult<Set<Integer>> listRoleResources(Integer roleId) {
        logger.info("[echo][被调用啦 roleId({})]", roleId);
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
        logger.info("[echo][被调用啦 adminId({})]", adminId);
        return success(permissionService.listAdminRoles(adminId));
    }

    @PostMapping("/assign-admin-role")
    @ApiOperation("赋予用户角色")
    public CommonResult<Boolean> assignAdminRole(PermissionAssignAdminRoleDTO assignAdminRoleDTO) {
        permissionService.assignAdminRole(assignAdminRoleDTO);
        return success(true);
    }

    @GetMapping("/list-permissions")
    @ApiOperation("查询权限对应资源")
    @ApiImplicitParam(name = "permissions", value = "资源编号列表", required = true)
    public Set<Integer> selectListByPermissions(@RequestParam("permissions") List<String> permissions) {
        return permissionService.selectListByPermissions(permissions);
    }

    @GetMapping("/list-resources")
    @ApiOperation("根据资源id获取角色资源信息")
    @ApiImplicitParam(name = "permissions", value = "资源编号列表", required = true)
    public List<RoleResourceVo> selectListByResourceIds(@RequestParam("permissions") List<Integer> permissions) {
        return permissionService.selectListByResourceIds(permissions);
    }


}