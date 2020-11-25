package com.mht.sc.fliter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.mht.common.api.CommonResult;
import com.mht.common.redis.RedisService;
import com.mht.common.utils.JwtTokenUtil;
import com.mht.sc.config.IgnoreUrls;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private IgnoreUrls ignoreUrls;

    @Autowired
    private RedisService redisService;

    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Value("${redis.expire.sysUserToken}")
    private Long REDIS_EXPIRE_SYS_USER_TOKEN;
    @Value("${redis.key.sysUserToken}")
    private String REDIS_KEY_SYS_USER_TOKEN;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //判断是否需要过滤
        String path = request.getURI().getPath();
        ArrayList<String> ignoreUrlUrls = ignoreUrls.getUrls();
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String s : ignoreUrlUrls) {
            if (pathMatcher.match(s, path)) {
                request = exchange.getRequest().mutate().header(JwtTokenUtil.AUTH_HEADER_KEY, "").build();
                exchange = exchange.mutate().request(request).build();
                return chain.filter(exchange);
            }
        }
        // 判断urls是否需要登录
        String token = exchange.getRequest().getHeaders().getFirst(JwtTokenUtil.AUTH_HEADER_KEY);
        if (StringUtils.isEmpty(token)) {
            //返回未授权错误
            CommonResult<String> forbidden = CommonResult.unauthorized("暂未登录");
            return error(response, forbidden);
        }
        //通过远程调用判断JWT是否合法
        try {
            String result = JwtTokenUtil.verifyToken(token);
            if (StrUtil.isBlank(result)) {
                //返回认证错误
                return error(response, CommonResult.validateFailed());
            }
            String key = REDIS_DATABASE + ":" + REDIS_KEY_SYS_USER_TOKEN + ":" + result;
            Long expire = redisService.getExpire(key);
            if (expire < 0) {
                return error(response, CommonResult.validateFailed());
            } else {
                redisService.set(key, token, REDIS_EXPIRE_SYS_USER_TOKEN);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return error(response, CommonResult.unauthorized("验证token失败"));
        }
        //将登录信息保存到下一级
        ServerHttpRequest newRequest = request.mutate().header(JwtTokenUtil.AUTH_HEADER_KEY, token).build();
        ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
        return chain.filter(newExchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private Mono<Void> error(ServerHttpResponse response, Object object) {
        //返回错误
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        String json = JSONObject.toJSONString(object);
        DataBuffer buffer = response.bufferFactory().wrap(json.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }

}
