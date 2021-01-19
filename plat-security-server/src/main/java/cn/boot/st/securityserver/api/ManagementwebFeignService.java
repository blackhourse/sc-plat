package cn.boot.st.securityserver.api;

import cn.boot.common.framework.constant.ServiceNameConstants;
import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.securityserver.api.fallback.AuthWebServiceFallbackFactory;
import cn.boot.st.securityserver.domain.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-11
 **/
@FeignClient(name = ServiceNameConstants.MANAGEMENT_WEB_SERVICE, fallbackFactory = AuthWebServiceFallbackFactory.class)
public interface ManagementwebFeignService {

    @GetMapping("/admin/info/{userName}")
    public CommonResult<UserDTO> getUserInfoByUserName(@PathVariable("userName") String userName);


}
