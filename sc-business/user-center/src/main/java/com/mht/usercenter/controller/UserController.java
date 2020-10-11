package com.mht.usercenter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    /**
     * 查询用户实体对象SysUser
     */
    @RequestMapping(value = "/user/name")
    public String selectByUsername(String username) {
        return username;
    }

}
