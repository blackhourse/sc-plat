package cn.boot.st.securityserver.api.fallback;

import cn.boot.common.framework.exception.util.GlobalException;
import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.securityserver.api.ManagementwebFeignService;
import cn.boot.st.securityserver.domain.UserDTO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static cn.boot.common.framework.constant.SystemErrorCodeConstants.SERVICE_DEGRADATION;

/**
 * @Classname ManageWebServiceFallbackFactory
 * @Description
 * @Date 2021/1/5
 * @Created by maht
 */
@Component
@Slf4j
public class AuthWebServiceFallbackFactory implements FallbackFactory<ManagementwebFeignService> {
    @Override
    public ManagementwebFeignService create(Throwable throwable) {
        return new ManagementwebFeignService() {

            @Override
            public CommonResult<UserDTO> getUserInfoByUserName(String userName) {
                log.error("auth-service方法异常，异常信息如下:{}", throwable);
                return CommonResult.error(new GlobalException(SERVICE_DEGRADATION));
            }
        };
    }

}
