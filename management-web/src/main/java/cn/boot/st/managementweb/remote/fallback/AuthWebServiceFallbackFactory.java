package cn.boot.st.managementweb.remote.fallback;

import cn.boot.common.framework.dataobject.dto.OAuth2AccessTokenRespDTO;
import cn.boot.common.framework.dataobject.dto.OAuth2CreateAccessTokenReqDTO;
import cn.boot.common.framework.exception.util.GlobalException;
import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.managementweb.remote.AuthFeignService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static cn.boot.common.framework.constant.SystemErrorCodeConstants.SERVICE_DEGRADATION;

/**
 * @Classname ManageWebServiceFallbackFactory
 * @Description
 * @Date 2021/1/5
 * @Created by maht
 */
@Component
@Slf4j
public class AuthWebServiceFallbackFactory implements FallbackFactory<AuthFeignService> {


    @Override
    public AuthFeignService create(Throwable throwable) {
        return new AuthFeignService(){

            @Override
            public CommonResult<OAuth2AccessTokenRespDTO> getInfoById(String token) {
                log.error("auth-service方法异常，异常信息如下:{}",throwable);
                return CommonResult.error(new GlobalException(SERVICE_DEGRADATION));
            }

            @Override
            public CommonResult<OAuth2AccessTokenRespDTO> createAccessToken(OAuth2CreateAccessTokenReqDTO auth2CreateAccessTokenReqDTO, HttpServletRequest request) {
                log.error("auth-service方法异常，异常信息如下:{}",throwable);
                return CommonResult.error(new GlobalException(SERVICE_DEGRADATION));
            }
        };
    }
}
