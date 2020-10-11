package com.mht.userorder.controller;

import com.mht.userorder.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserOrderController {


    @Autowired
    private UserOrderService userOrderService;

    /**
     * 查询用户实体对象SysUser
     */
    @GetMapping(value = "/order/{key}")
    public String selectByUsername(@PathVariable String key) {
        return userOrderService.getUserInfo(key);
    }

}
