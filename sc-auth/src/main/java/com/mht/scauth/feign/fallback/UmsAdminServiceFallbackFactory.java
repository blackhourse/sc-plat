package com.mht.scauth.feign.fallback;

import com.mht.scauth.feign.UmsAdminService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * menuService降级工场
 *
 * @author zlt
 * @date 2019/1/18
 */
@Slf4j
@Component
public class UmsAdminServiceFallbackFactory implements FallbackFactory<UmsAdminService> {

    @Override
    public UmsAdminService create(Throwable throwable) {
        log.error("调用loadUserByUsername异常：{}", throwable);
        return null;
    }
}
