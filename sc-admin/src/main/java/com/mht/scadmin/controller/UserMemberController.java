package com.mht.scadmin.controller;

import com.mht.core.domain.UserDto;
import com.mht.scadmin.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-11-11 15:51
 **/
@Api(tags = "UserMemberController", description = "会员登录注册管理")
@RestController
@RequestMapping("userMember")
public class UserMemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation("根据用户名获取通用用户信息")
    @RequestMapping(value = "/loadByUsername", method = RequestMethod.GET)
    @ResponseBody
    public UserDto loadUserByUsername(@RequestParam String username) {
        return memberService.loadUserByUsername(username);
    }

}
