//package com.mht.scauth.feign.fallback;
//
//import com.mht.scauth.feign.UmsMemberService;
//import feign.hystrix.FallbackFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
///**
// * menuService降级工场
// *
// * @author zlt
// * @date 2019/1/18
// */
//@Slf4j
//@Component
//public class UmsMemberServiceFallbackFactory implements FallbackFactory<UmsMemberService> {
//    @Override
//    public UmsMemberService create(Throwable throwable) {
//        return roleIds -> {
//            log.error("调用loadUserByUsername异常：{}", roleIds, throwable);
//            return null;
//        };
//    }
//}
