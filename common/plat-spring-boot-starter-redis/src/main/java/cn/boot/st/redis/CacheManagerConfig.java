//package cn.boot.st.redis;
//
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
//import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
///**
// * @program: sc-plat
// * @author: maht
// * @create: 2020-12-18
// **/
//@Configuration
//@ConditionalOnMissingBean(CacheManagerCustomizers.class)
//public class CacheManagerConfig {
//
//    @Bean
//    public CacheManagerCustomizers cacheManagerCustomizers(
//            ObjectProvider<List<CacheManagerCustomizer<?>>> customizers) {
//        return new CacheManagerCustomizers(customizers.getIfAvailable());
//    }
//}