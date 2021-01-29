package cn.boot.st.gateway.filter;

import cn.boot.common.framework.constant.AuthConstants;
import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.redis.RedisService;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.nimbusds.jose.JWSObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

import static cn.boot.st.gateway.constant.GatewayErrorCodeConstants.TOKEN_INVALID_OR_EXPIRED;

/**
 * 黑名单token过滤器
 */
@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {


    @Autowired
    private RedisService redisService;
    @Value("${spring.application.name}")
    private String applicationName;


    @Override
    public int getOrder() {
        return 0;
    }

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {


        //判断是否为swagger的请求,放行swagger请求
        if (exchange.getRequest().getURI().getPath().contains("/v2/api-docs")) {
            //放行
            return chain.filter(exchange);
        }

        String token = exchange.getRequest().getHeaders().getFirst(AuthConstants.JWT_TOKEN_HEADER);
        if (StrUtil.isBlank(token)) {
            return chain.filter(exchange);
        }

        token = token.replace(AuthConstants.JWT_TOKEN_PREFIX, Strings.EMPTY);
        JWSObject jwsObject = JWSObject.parse(token);
        String payload = jwsObject.getPayload().toString();


        // 黑名单token(登出、修改密码)校验
        JSONObject jsonObject = JSONUtil.parseObj(payload);
        // JWT唯一标识
        String jti = jsonObject.getStr("jti");

        Boolean isBlack = redisService.hasKey(AuthConstants.TOKEN_BLACKLIST_PREFIX + jti);
        if (isBlack) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.OK);
            response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            response.getHeaders().set("Access-Control-Allow-Origin", "*");
            response.getHeaders().set("Cache-Control", "no-cache");
            String body = JSONUtil.toJsonStr(CommonResult.error(TOKEN_INVALID_OR_EXPIRED.getCode(),
                    TOKEN_INVALID_OR_EXPIRED.getMessage()));
            DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(Charset.forName("UTF-8")));
            return response.writeWith(Mono.just(buffer));
        }
        ServerHttpRequest request = exchange.getRequest().mutate()
                .headers(httpHeaders -> {
                            httpHeaders.add(AuthConstants.JWT_PAYLOAD_KEY, payload);
                            httpHeaders.add(AuthConstants.JWT_USER_ID_KEY, jsonObject.getStr(AuthConstants.JWT_USER_ID_KEY));
                        }
                )
                .build();
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }
}
