package com.mht.sc.scauthserver.service;

import com.mht.sc.scauthserver.domain.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by macro on 2020/7/16.
 */
@FeignClient("mall-portal")
public interface UmsMemberService {
    @GetMapping("/sso/loadByUsername")
    UserDto loadUserByUsername(@RequestParam(value = "username") String username);
}
