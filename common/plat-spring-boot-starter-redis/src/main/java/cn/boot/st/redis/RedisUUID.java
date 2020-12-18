package cn.boot.st.redis;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-18
 **/
@Component
public class RedisUUID {
    @Autowired
    private RedisTemplate redisTemplate;

    // 过期时间
    private final static long expiration = 1000 * 60 * 5;

    // 过期前1分钟
    private final static long lastTime = 1000 * 60;

    public String create(String key){
        if(StrUtil.isBlank(key)){
            return null;
        }
        String secretKey;
        if(redisTemplate.hasKey(key)){
            if(redisTemplate.boundHashOps(key).getExpire() < lastTime){
                redisTemplate.opsForValue().set(key, SecureUtil.md5(UUID.randomUUID().toString()),expiration, TimeUnit.SECONDS);
            }
            secretKey = (String) redisTemplate.opsForValue().get(key);
        }else{
            secretKey = SecureUtil.md5(UUID.randomUUID().toString());
            redisTemplate.opsForValue().set(key,secretKey,expiration,TimeUnit.SECONDS);
        }
        return secretKey;
    }

    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }
}
