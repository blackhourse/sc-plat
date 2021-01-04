package cn.boot.st.managementweb.service.admin.impl;

import cn.boot.common.framework.dataobject.vo.AdminUserTokenInfo;
import cn.boot.st.managementweb.service.admin.AdminCacheService;
import cn.boot.st.redis.RedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-29
 **/
@Service
public class AdminCacheServiceImpl implements AdminCacheService {
    @Resource
    private RedisService redisService;
    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Value("${redis.backend-web.token}")
    private String BackendWebAccessToken;

    @Value("${redis.backend-web.refresh-token-expire-time-millis}")
    private Long refreshTokenExpireTimeMillis;
    @Value("${redis.backend-web.access-token-expire-time-millis}")
    private Long accessTokenExpireTimeMillis;

    @Override
    public void setAuthToken(String token, AdminUserTokenInfo adminUserTokenInfo) {
        String key = REDIS_DATABASE + ":" + BackendWebAccessToken + ":" + token;
        redisService.set(key, adminUserTokenInfo, accessTokenExpireTimeMillis);
    }


}
