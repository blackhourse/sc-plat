package cn.boot.st.portal.controller;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.portal.service.LoginService;
import cn.boot.st.portal.vo.UmsLoginCodeVo;
import cn.boot.st.portal.vo.UmsMemberLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-27
 **/
@RestController
@RequestMapping("portal")
@Api(tags = "app")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("phone-code")
    @ApiOperation(value = "获取短信验证码")
    public CommonResult<UmsLoginCodeVo> getPhoneCode(String phone) {
        return CommonResult.success(loginService.getMobileCode(phone));
    }

    @PostMapping("login")
    @ApiOperation(value = "登录")
    public CommonResult<UmsMemberLoginVo> login(String phone, Integer code) {
        return CommonResult.success(loginService.loginByPhoneCode(phone, code));
    }


}
