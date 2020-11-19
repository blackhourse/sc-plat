package com.mht.sc.fliter;

import com.mht.common.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();

        // 判断urls是否需要登录
        String token = exchange.getRequest().getHeaders().getFirst(JwtTokenUtil.AUTH_HEADER_KEY);
        if (StringUtils.isBlank(token)) {
            serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 未登录
        }
        // 判断token有效期
        // 判断权限

        return null;
    }

    public int getOrder() {
        return 0;
    }


}
