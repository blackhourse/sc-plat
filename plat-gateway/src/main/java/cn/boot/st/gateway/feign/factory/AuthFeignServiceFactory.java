package cn.boot.st.gateway.feign.factory;

import cn.boot.common.framework.dataobject.dto.OAuth2AccessTokenRespDTO;
import cn.boot.st.gateway.feign.AuthFeignService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-09 09:21
 **/
@Slf4j
@Component
public class AuthFeignServiceFactory implements FallbackFactory<AuthFeignService> {


    @Override
    public AuthFeignService create(Throwable throwable) {
        return new AuthFeignService() {
            @Override
            public OAuth2AccessTokenRespDTO getInfoById(String token) {
                log.error("获取token信息失败:{}", token);
                return null;
            }
        };
    }
}
