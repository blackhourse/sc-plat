package cn.boot.st.managementweb.remote;

import cn.boot.common.framework.constant.ServiceNameConstants;
import cn.boot.common.framework.dataobject.dto.OAuth2AccessTokenRespDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-11
 **/
@FeignClient(name = ServiceNameConstants.SECURITY_SERVER_SERVICE)
public interface AuthFeignService {

    @GetMapping("/auth/id-info")
    public OAuth2AccessTokenRespDTO getInfoById(@RequestParam("token") String token);

}
