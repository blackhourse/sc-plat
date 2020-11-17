package com.mht.sc.scadmin.controller;


import com.mht.sc.scadmin.entity.SysUser;
import com.mht.sc.scadmin.service.SysUserService;
import com.mht.sc.scadmin.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author maht
 * @since 2020-11-17
 */

@RestController
@RequestMapping("/sysUser")
@Api(value = "用户模块api")
public class SysUserController {

    private static final String ADMIN_CHANGE_MSG = "超级管理员不给予修改";


    @Autowired
    private SysUserService sysUserService;


    @PostMapping("/users/saveOrUpdate")
    @ApiOperation(value = "新增/修改用户")
    public Result<Long> saveOrUpdate(@RequestBody SysUser sysUser) {
        return Result.succeed(sysUserService.saveOrUpdateUser(sysUser));
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @GetMapping(value = "/users/{id}")
    @ApiOperation(value = "删除用户")
    public Result delete(@PathVariable Long id) {
        if (checkAdmin(id)) {
            return Result.failed(ADMIN_CHANGE_MSG);
        }
        sysUserService.delUser(id);
        return Result.succeed("删除成功");
    }

    /**
     * 重置密码
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/users/{id}/password")
    @ApiOperation(value = "重置密码")
    public Result resetPassword(@PathVariable Long id) {
        if (checkAdmin(id)) {
            return Result.failed(ADMIN_CHANGE_MSG);
        }
        sysUserService.updatePassword(id, null, null);
        return Result.succeed("修改成功");
    }

    /**
     * 用户自己修改密码
     */
    @PostMapping(value = "/users/password")
    public Result updatePassword(@RequestBody SysUser sysUser) {
        if (checkAdmin(sysUser.getId())) {
            return Result.failed(ADMIN_CHANGE_MSG);
        }
        sysUserService.updatePassword(sysUser.getId(), sysUser.getOldPassword(), sysUser.getNewPassword());
        return Result.succeed("修改成功");
    }

    /**
     * 修改用户状态
     */
    @PostMapping(value = "/users/enable")
    public Result updateEnable(@RequestBody SysUser sysUser) {
        if (checkAdmin(sysUser.getId())) {
            return Result.failed(ADMIN_CHANGE_MSG);
        }
        sysUserService.updateEnable(sysUser.getId(), sysUser.getEnabled());
        return Result.succeed("修改成功");
    }


    /**
     * 是否超级管理员
     */
    private boolean checkAdmin(long id) {
        return id == 1L;
    }
}

