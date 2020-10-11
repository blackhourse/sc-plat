package com.mht.userorder.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sc-user-center", fallback = Exception.class)
public interface UserService {


    @RequestMapping("user/name")
    String selectByUsername(@RequestParam(name = "username") String username);

}
