package cn.boot.st.managementweb.remote;

import cn.boot.common.framework.constant.ServiceNameConstants;
import cn.boot.common.framework.dataobject.dto.OAuth2AccessTokenRespDTO;
import cn.boot.common.framework.dataobject.dto.OAuth2CreateAccessTokenReqDTO;
import cn.boot.common.framework.vo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-11
 **/
@FeignClient(name = ServiceNameConstants.SECURITY_SERVER_SERVICE)
public interface AuthFeignService {

    @GetMapping("/auth/id-info")
    public CommonResult<OAuth2AccessTokenRespDTO> getInfoById(@RequestParam("token") String token);

    /**
     * 创建token
     *
     * @param auth2CreateAccessTokenReqDTO
     * @param request
     * @return
     */
    @PostMapping("/auth/crateToken")
    public CommonResult<OAuth2AccessTokenRespDTO> createAccessToken(@RequestBody OAuth2CreateAccessTokenReqDTO auth2CreateAccessTokenReqDTO, @RequestParam("request") HttpServletRequest request);


}
