//package com.mht.scauth.feign;
//
//import com.mht.core.domain.UserDto;
//import com.mht.scauth.feign.fallback.UmsMemberServiceFallbackFactory;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
///**
// * Created by macro on 2020/7/16.
// */
//@FeignClient(value = "sc-admin", fallbackFactory = UmsMemberServiceFallbackFactory.class, decode404 = true)
//public interface UmsMemberService {
//    @GetMapping("/userMember/loadByUsername")
//    UserDto loadUserByUsername(@RequestParam(name = "username") String username);
//}
