package com.mht.userorder.service.impl;

import com.mht.userorder.feign.UserService;
import com.mht.userorder.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired
    private UserService userService;

    public String getUserInfo(String key) {
        String s = userService.selectByUsername(key);
        System.out.println(s);
        return s;
    }
}
