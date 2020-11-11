package com.mht.scadmin.controller;

import com.mht.core.domain.UserDto;
import com.mht.scadmin.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-11-11 15:51
 **/
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RestController
@RequestMapping("userAdmin")
public class UserAdminController {

    @Autowired
    private AdminService adminService;


    @ApiOperation("根据用户名获取通用用户信息")
    @RequestMapping(value = "/loadByUsername", method = RequestMethod.GET)
    @ResponseBody
    public UserDto loadUserByUsername(@RequestParam String username) {
        UserDto userDTO = adminService.loadUserByUsername(username);
        return userDTO;
    }


}
