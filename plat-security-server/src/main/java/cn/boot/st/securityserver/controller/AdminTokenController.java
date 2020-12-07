package cn.boot.st.securityserver.controller;

import cn.boot.common.framework.util.HttpUtil;
import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.security.dto.OAuth2CreateAccessTokenReqDTO;
import cn.boot.st.securityserver.service.OAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static cn.boot.common.framework.vo.CommonResult.success;

/**
 * @Classname AdminTokenController
 * @Description
 * @Date 2020/12/7
 * @Created by maht
 */
@Api(tags = "认证 API")
@RestController
@RequestMapping("/auth")
public class AdminTokenController {

    @Autowired
    private OAuthService oAuthService;

    @PostMapping("/crateToken")
    @ApiOperation("获取Token")
    public CommonResult createAccessToken(OAuth2CreateAccessTokenReqDTO auth2CreateAccessTokenReqDTO, HttpServletRequest request) {
        String ip = HttpUtil.getIp(request);
        auth2CreateAccessTokenReqDTO.setCreateIp(ip);
        return success(oAuthService.createAccessToken(auth2CreateAccessTokenReqDTO));
    }

    @GetMapping("/test")
    @ApiOperation("test")
    public CommonResult test(String name) {
        System.out.println(name);
        return success("");
    }


}
