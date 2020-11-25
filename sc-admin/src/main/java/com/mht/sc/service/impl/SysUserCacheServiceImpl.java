package com.mht.sc.service.impl;

import com.mht.common.redis.RedisService;
import com.mht.sc.service.SysUserCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SysUserCacheServiceImpl implements SysUserCacheService {

    @Autowired
    private RedisService redisService;

    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.expire.phoneCode}")
    private Long REDIS_EXPIRE_AUTH_CODE;
    @Value("${redis.key.member}")
    private String REDIS_KEY_MEMBER;
    @Value("${redis.key.phoneCode}")
    private String REDIS_KEY_AUTH_CODE;


    @Value("${redis.expire.sysUserToken}")
    private Long REDIS_EXPIRE_SYS_USER_TOKEN;
    @Value("${redis.key.sysUserToken}")
    private String REDIS_KEY_SYS_USER_TOKEN;


    @Override
    public void setAuthCode(String telephone, String authCode) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_AUTH_CODE + ":" + telephone;
        redisService.set(key, authCode, REDIS_EXPIRE_AUTH_CODE);
    }

    @Override
    public String getAuthCode(String telephone) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_AUTH_CODE + ":" + telephone;
        return (String) redisService.get(key);
    }

    @Override
    public void setSysUserToken(String token, String id) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_SYS_USER_TOKEN + ":" + id;
        redisService.set(key, token, REDIS_EXPIRE_SYS_USER_TOKEN);
    }

    @Override
    public void delToken(String id) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_SYS_USER_TOKEN + ":" + id;
        redisService.del(key);
    }
}
