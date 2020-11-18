package com.mht.sc.scadmin.controller;


import com.mht.sc.scadmin.dto.SysRoleAddOrUpdateDto;
import com.mht.sc.scadmin.entity.SysRole;
import com.mht.sc.scadmin.service.SysRoleService;
import com.mht.sc.scadmin.util.PageResult;
import com.mht.sc.scadmin.util.CommonResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author maht
 * @since 2020-11-17
 */
@Slf4j
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {


    @Autowired
    private SysRoleService sysRoleService;


    /**
     * 后台管理查询角色
     *
     * @param params
     * @return
     */
    @ApiOperation(value = "后台管理查询角色")
    @GetMapping("/roles")
    public PageResult<SysRole> findRoles(@RequestParam Map<String, Object> params) {
        return sysRoleService.findRoles(params);
    }

    /**
     * 用户管理查询所有角色
     *
     * @return
     */
    @ApiOperation(value = "后台管理查询角色")
    @GetMapping("/allRoles")
    public CommonResult<List<SysRole>> findAll() {
        List<SysRole> result = sysRoleService.findAll();
        return CommonResult.success(result);
    }

    /**
     * 角色新增或者更新
     *
     * @param sysRoleAddOrUpdateDto
     * @return
     */
    @PostMapping("/roles/saveOrUpdate")
    public CommonResult saveOrUpdate(@RequestBody SysRoleAddOrUpdateDto sysRoleAddOrUpdateDto) throws Exception {
        return sysRoleService.saveOrUpdateRole(sysRoleAddOrUpdateDto);
    }

    /**
     * 后台管理删除角色
     * delete /role/1
     *
     * @param id
     */
    @ApiOperation(value = "后台管理删除角色")
    @DeleteMapping("/roles/{id}")
    public CommonResult deleteRole(@PathVariable Long id) {
        try {
            if (id == 1L) {
                return CommonResult.failed("管理员不可以删除");
            }
            sysRoleService.deleteRole(id);
            return CommonResult.success("操作成功");
        } catch (Exception e) {
            log.error("role-deleteRole-error", e);
            return CommonResult.failed("操作失败");
        }
    }


}

