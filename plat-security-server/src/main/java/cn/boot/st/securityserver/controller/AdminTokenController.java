package cn.boot.st.securityserver.controller;

import cn.boot.common.framework.dataobject.dto.OAuth2AccessTokenRespDTO;
import cn.boot.common.framework.dataobject.dto.OAuth2CreateAccessTokenReqDTO;
import cn.boot.common.framework.dataobject.vo.PassportAccessTokenVO;
import cn.boot.common.framework.util.HttpUtil;
import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.security.annotations.RequiresPermissions;
import cn.boot.st.security.core.context.AdminSecurityContextHolder;
import cn.boot.st.securityserver.service.oauth.OAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public CommonResult<OAuth2AccessTokenRespDTO> createAccessToken(@RequestBody OAuth2CreateAccessTokenReqDTO auth2CreateAccessTokenReqDTO, HttpServletRequest request) {
        String ip = HttpUtil.getIp(request);
        auth2CreateAccessTokenReqDTO.setCreateIp(ip);
        return success(oAuthService.createAccessToken(auth2CreateAccessTokenReqDTO));
    }

    @GetMapping("/info")
    @ApiOperation(value = "获得当前管理员信息")
    @RequiresPermissions("system:admin:page")
    public CommonResult<Integer> getInfo() {
        Integer adminId = AdminSecurityContextHolder.getAdminId();
        return success(adminId);
    }


    @PostMapping("/refresh-token")
    @ApiOperation("刷新令牌")
    @RequiresPermissions
    public CommonResult<PassportAccessTokenVO> refreshToken(@RequestParam("refreshToken") String refreshToken,
                                                            HttpServletRequest request) {
        return success(oAuthService.refreshToken(refreshToken, HttpUtil.getIp(request)));
    }


}
