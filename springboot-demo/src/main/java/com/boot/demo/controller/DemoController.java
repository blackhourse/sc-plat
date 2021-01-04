package com.boot.demo.controller;

import cn.boot.st.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-30
 **/
@RestController("demo")
public class DemoController {


    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Value("${redis.backend-web.token}")
    private String BackendWebAccessToken;

    @Value("${redis.backend-web.refresh-token-expire-time-millis}")
    private Long refreshTokenExpireTimeMillis;
    @Value("${redis.backend-web.access-token-expire-time-millis}")
    private Long accessTokenExpireTimeMillis;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisService redisService;

    @GetMapping("test")
    public String test(String str) {
        String key = REDIS_DATABASE + ":" + BackendWebAccessToken + ":" + str;
        redisService.set(key, "123", accessTokenExpireTimeMillis);
        return "123";
    }

    @GetMapping("query")
    public String query(String str) {
        String key = REDIS_DATABASE + ":" + BackendWebAccessToken + ":" + str;
        Long expire1 = redisTemplate.getExpire(key);
        Object o = redisTemplate.opsForValue().get(key);

        long expire = redisService.getExpire(key);
        System.out.println(expire);

        return String.valueOf(expire1);
    }

}
