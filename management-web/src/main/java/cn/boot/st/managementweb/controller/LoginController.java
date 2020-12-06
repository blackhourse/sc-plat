package cn.boot.st.managementweb.controller;

import cn.boot.common.framework.util.HttpUtil;
import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.managementweb.dataobject.dto.PassportLoginDTO;
import cn.boot.st.managementweb.dataobject.vo.PassportAccessTokenVO;
import cn.boot.st.managementweb.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static cn.boot.common.framework.vo.CommonResult.success;

/**
 * @Classname LoginController
 * @Description
 * @Date 2020/12/6
 * @Created by maht
 */
@Api(tags = "登录 API")
@RestController
@RequestMapping("/login")
public class LoginController {



    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ApiOperation("账号密码登陆")
    public CommonResult<PassportAccessTokenVO> login(PassportLoginDTO loginDTO,
                                                     HttpServletRequest request) {
        return success(loginService.login(loginDTO, HttpUtil.getIp(request)));
    }



}
