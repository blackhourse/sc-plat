package com.mht.sc.controller;


import com.mht.common.api.CommonPage;
import com.mht.common.api.CommonResult;
import com.mht.sc.dto.*;
import com.mht.sc.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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

    @ApiOperation("用户列表")
    @PostMapping(value = "/SysUserList")
    @ResponseBody
    public CommonPage SysUserList(@Validated @RequestBody SysUserListDto sysUserListDto) {
        return CommonPage.restPage(sysUserService.sysUserList(sysUserListDto));
    }


    @ApiOperation("获取验证码")
    @GetMapping(value = "/getAuthCode")
    @ResponseBody
    public CommonResult<String> getAuthCode(@RequestParam String telephone) {
        return CommonResult.success(sysUserService.generateAuthCode(telephone));
    }

    @ApiOperation("用户名-密码登录")
    @PostMapping(value = "/login")
    @ResponseBody
    public CommonResult<String> login(@Validated @RequestBody SysUserLoginPwdDto sysUserLoginPwdDto) {
        return CommonResult.success(sysUserService.login(sysUserLoginPwdDto));
    }

    @ApiOperation("手机号-验证码登录")
    @PostMapping(value = "/loginPhone")
    @ResponseBody
    public CommonResult<String> loginPhone(@Validated @RequestBody SysUserLoginPhoneDto sysUserLoginPwdDto) {
        return CommonResult.success(sysUserService.loginPhone(sysUserLoginPwdDto));
    }

    @ApiOperation(value = "账号推出")
    @PostMapping(value = "/loginOut")
    @ResponseBody
    public CommonResult<String> loginOut() throws Exception {
        sysUserService.loginOut();
        return CommonResult.success();
    }


    @PostMapping("/users/saveOrUpdate")
    @ApiOperation(value = "新增/修改用户")
    public CommonResult<Long> saveOrUpdate(@RequestBody SysUserDto sysUserDto) {
        return CommonResult.success(sysUserService.saveOrUpdateUser(sysUserDto));
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @GetMapping(value = "/users/{id}")
    @ApiOperation(value = "删除用户")
    public CommonResult delete(@PathVariable Long id) {
        if (checkAdmin(id)) {
            return CommonResult.failed(ADMIN_CHANGE_MSG);
        }
        sysUserService.delUser(id);
        return CommonResult.success("删除成功");
    }

    /**
     * 重置密码
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/users/{id}/password")
    @ApiOperation(value = "重置密码")
    public CommonResult resetPassword(@PathVariable Long id) {
        if (checkAdmin(id)) {
            return CommonResult.failed(ADMIN_CHANGE_MSG);
        }
        sysUserService.updatePassword(id, null, null);
        return CommonResult.success("修改成功");
    }

    /**
     * 用户自己修改密码
     */
    @PostMapping(value = "/users/password")
    public CommonResult updatePassword(@RequestBody SysUserPwdDto sysUserPwdDto) {
        if (checkAdmin(sysUserPwdDto.getId())) {
            return CommonResult.failed(ADMIN_CHANGE_MSG);
        }
        return sysUserService.updatePassword(sysUserPwdDto.getId(), sysUserPwdDto.getOldPassword(), sysUserPwdDto.getNewPassword());
    }

    /**
     * 修改用户状态
     */
    @PostMapping(value = "/users/enable")
    public CommonResult updateEnable(@RequestBody SysUserEnableDto sysUserEnableDto) {
        if (checkAdmin(sysUserEnableDto.getId())) {
            return CommonResult.failed(ADMIN_CHANGE_MSG);
        }
        sysUserService.updateEnable(sysUserEnableDto.getId(), sysUserEnableDto.getEnabled());
        return CommonResult.success("修改成功");
    }


    /**
     * 是否超级管理员
     */
    private boolean checkAdmin(long id) {
        return id == 1L;
    }


    /**
     * 管理后台给用户分配角色
     *
     * @param id
     * @param roleIds
     */
    @PostMapping("/users/{id}/roles")
    public void setRoleToUser(@PathVariable Long id, @RequestBody Set<Long> roleIds) {
        sysUserService.setRoleToUser(id, roleIds);
    }

}

