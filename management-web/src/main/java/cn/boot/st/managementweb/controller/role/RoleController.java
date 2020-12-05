package cn.boot.st.managementweb.controller.role;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.managementweb.context.AdminSecurityContextHolder;
import cn.boot.st.managementweb.dataobject.dto.RoleCreateDTO;
import cn.boot.st.managementweb.dataobject.dto.RolePageDTO;
import cn.boot.st.managementweb.dataobject.dto.RoleUpdateDTO;
import cn.boot.st.managementweb.dataobject.vo.RoleVO;
import cn.boot.st.managementweb.service.role.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static cn.boot.common.framework.vo.CommonResult.success;

/**
 * @Classname RoleController
 * @Description
 * @Date 2020/12/5 17:54
 * @Created by maht
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping(value = "role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    @ApiOperation("创建角色")
    public CommonResult<Integer> createRole(@Valid RoleCreateDTO createDTO) {
        return success(roleService.createRole(createDTO, AdminSecurityContextHolder.getAdminId()));
    }


    @PostMapping("/update")
    @ApiOperation("更新角色")
    public CommonResult<Boolean> updateRole(@Valid RoleUpdateDTO updateDTO) {
        roleService.updateRole(updateDTO);
        return success(true);
    }

    @PostMapping("/delete")
    @ApiOperation("删除角色")
    @ApiImplicitParam(name = "roleId", value = "角色编号", required = true)
    public CommonResult<Boolean> deleteRole(@RequestParam("roleId") Integer roleId) {
        roleService.deleteRole(roleId);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得角色")
    @ApiImplicitParam(name = "roleId", value = "角色编号", required = true)
    public CommonResult<RoleVO> role(@RequestParam("roleId") Integer roleId) {
        return success(roleService.getRole(roleId));
    }


    @GetMapping("/list-all")
    @ApiOperation("获得所有角色列表")
    public CommonResult<List<RoleVO>> listAllRoles() {
        return success(roleService.listAllRoles());
    }

    @GetMapping("/list")
    @ApiOperation("获得角色列表")
    @ApiImplicitParam(name = "roleIds", value = "角色编号列表", required = true)
    public CommonResult<List<RoleVO>> listRoles(@RequestParam("roleIds") List<Integer> roleIds) {
        return success(roleService.listRoles(roleIds));
    }

    @GetMapping("/page")
    @ApiOperation("获得角色分页")
    public CommonResult<PageResult<RoleVO>> pageRole(RolePageDTO pageDTO) {
        return success(roleService.pageRole(pageDTO));
    }

}
