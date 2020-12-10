package cn.boot.st.gateway.feign;

import cn.boot.common.framework.dataobject.dto.OAuth2AccessTokenRespDTO;
import cn.boot.st.gateway.feign.factory.AuthFeignServiceFactory;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-08 16:51
 **/
@FeignClient(name = "security-server", fallbackFactory = AuthFeignServiceFactory.class, decode404 = true)
public interface AuthFeignService {

    /**
     * 根据id获取详情
     * @param token
     * @return
     */
    @GetMapping("/id-info")
    OAuth2AccessTokenRespDTO getInfoById(@RequestParam("token") String token);


}
