package com.mht.scauth.feign;

import com.mht.core.domain.UserDto;
import com.mht.scauth.feign.fallback.UmsAdminServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by macro on 2019/10/18.
 */
@FeignClient(value = "sc-admin", fallbackFactory = UmsAdminServiceFallbackFactory.class, decode404 = true)
public interface UmsAdminService {

    @GetMapping("/admin/loadByUsername")
    UserDto loadUserByUsername(@RequestParam(name = "username") String username);

    @GetMapping("/userMember/loadByUsername")
    UserDto loadUserByUsername2(@RequestParam(name = "username") String username);


}
