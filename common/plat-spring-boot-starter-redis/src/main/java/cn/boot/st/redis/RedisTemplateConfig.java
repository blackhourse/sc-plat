//package cn.boot.st.redis;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
///**
// * @program: sc-plat
// * @author: maht
// * @create: 2020-12-18
// **/
//
//@Configuration
////@EnableCaching //开启注解
//public class RedisTemplateConfig {
//    /*
//
//        private static final StringRedisSerializer STRING_SERIALIZER = new StringRedisSerializer();
//        private static final GenericJackson2JsonRedisSerializer JACKSON__SERIALIZER = new GenericJackson2JsonRedisSerializer();
//
//        @Bean
//        public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//            //设置缓存过期时间
//            RedisCacheConfiguration redisCacheCfg = RedisCacheConfiguration.defaultCacheConfig();
//            redisCacheCfg.entryTtl(Duration.ofMinutes(1))
//                    .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(STRING_SERIALIZER))
//                    .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(JACKSON__SERIALIZER));
//            return RedisCacheManager.builder(RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory))
//                    .cacheDefaults(redisCacheCfg)
//                    .build();
//        }
//
//        @Bean
//        @ConditionalOnMissingBean(name = "redisTemplate")
//        public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//            // 配置redisTemplate
//            RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//            redisTemplate.setConnectionFactory(factory);
//            // key序列化
//            redisTemplate.setKeySerializer(STRING_SERIALIZER);
//            // value序列化
//            redisTemplate.setValueSerializer(JACKSON__SERIALIZER);
//            // Hash key序列化
//            redisTemplate.setHashKeySerializer(STRING_SERIALIZER);
//            // Hash value序列化
//            redisTemplate.setHashValueSerializer(JACKSON__SERIALIZER);
//            redisTemplate.afterPropertiesSet();
//            return redisTemplate;
//        }
//    */
//
//    @Bean
//    @SuppressWarnings("all")
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//
//        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
//        template.setConnectionFactory(factory);
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        //key采用String的序列化方式
//        template.setKeySerializer(stringRedisSerializer);
//        //hash的key也采用String的序列化方式
//        template.setHashKeySerializer(stringRedisSerializer);
//        //value序列化方式采用jackson
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        //hash的value序列化方式采用jackson
//        template.setHashValueSerializer(jackson2JsonRedisSerializer);
//        template.afterPropertiesSet();
//        return template;
//    }
//
//}
