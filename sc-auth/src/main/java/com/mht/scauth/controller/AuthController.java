package com.mht.scauth.controller;

import com.mht.core.api.CommonResult;
import com.mht.core.domain.UserDto;
import com.mht.scauth.feign.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-11-11 17:12
 **/
@RestController
@Api(tags = "AuthController", description = "认证中心登录认证")
@RequestMapping("/oauth")
public class AuthController {
    @Autowired
    private UmsAdminService adminService;

//    @Autowired
//    private UmsMemberService umsMemberService;

    @GetMapping("test")
    @ApiOperation("根据用户名获取通用用户信息")
    public CommonResult<UserDto> test(@RequestParam String username) {
        UserDto userDto = adminService.loadUserByUsername(username);
        return CommonResult.success(userDto);
    }

    @GetMapping("test2")
    @ApiOperation("根据用户名获取通用用户信息2")
    public CommonResult<UserDto> test2(@RequestParam String username) {
        UserDto userDto = adminService.loadUserByUsername2(username);
        return CommonResult.success(userDto);
    }

}
