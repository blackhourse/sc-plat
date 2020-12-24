package cn.boot.st.gateway.filter;

import cn.boot.st.gateway.config.IgnoreUrlsConfig;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-10 14:25
 * @desc: 认证过滤
 **/
@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        List<String> ignoreUrls = ignoreUrlsConfig.getUrls();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                exchange = exchange.mutate().request(request).build();
                return chain.filter(exchange);
            }
        }
//      /management-web/admin/roleIds
        String path = uri.getPath();
        String applicationName = path.split("/")[1];
        getRequiresPermissions(applicationName);

        redisTemplate.opsForValue().set("1","2");
        Object o = redisTemplate.opsForValue().get("1");
        System.out.println(o);

        return chain.filter(exchange);
    }



    @Override
    public int getOrder() {
        return 0;
    }


    private void getRequiresPermissions(String key) {
        Object o = redisTemplate.opsForValue().get(key);
        System.out.println(o);
    }


}
