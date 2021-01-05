package cn.boot.st.gateway.filter;

import cn.boot.common.framework.constant.AuthConstants;
import cn.boot.common.framework.dataobject.dto.PermissionVo;
import cn.boot.common.framework.dataobject.entity.PermissionEntityVO;
import cn.boot.common.framework.dataobject.vo.AdminUserTokenInfo;
import cn.boot.common.framework.enums.SystemClientType;
import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.gateway.config.IgnoreUrlsConfig;
import cn.boot.st.gateway.remote.ManageWebService;
import cn.boot.st.redis.RedisService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static cn.boot.common.framework.exception.enums.GlobalErrorCodeConstants.*;

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
    private RedisService redisService;

    @Autowired
    private ManageWebService manageWebService;
    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Value("${redis.backend-web.token}")
    private String BACKEND_WEB_ACCESS_TOKEN;

    private static final String PERSISTENCE_NAME = "PERSISTENCE_NAME";


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
        // 判断用户类型
        String clientType = request.getHeaders().getFirst(AuthConstants.clientType);
        SystemClientType instance = SystemClientType.getInstance(clientType);
        if (instance == null) {
            return setResponseInfo(exchange.getResponse(), BAD_REQUEST.getCode(), BAD_REQUEST.getMessage());
        }
        // 获取token
        String token = getToken(request);
        if (token == null) {
            return setResponseInfo(exchange.getResponse(), UNAUTHORIZED.getCode(), UNAUTHORIZED.getMessage());
        }
        String key = REDIS_DATABASE + ":" + BACKEND_WEB_ACCESS_TOKEN + ":" + token;
        long expire = redisService.getExpire(key);
        AdminUserTokenInfo authInfo = (AdminUserTokenInfo) redisService.get(key);
        if (expire < 0) {
            return setResponseInfo(exchange.getResponse(), UNAUTHORIZED.getCode(), UNAUTHORIZED.getMessage());
        }
        String path = request.getURI().getPath();
        CommonResult<List<Integer>> infoById = manageWebService.getInfoById(authInfo.getId());
        infoById.checkError();
        List<Integer> roleIds = infoById.getData();
        if (CollUtil.isEmpty(roleIds)) {
            return setResponseInfo(exchange.getResponse(), FORBIDDEN.getCode(), FORBIDDEN.getMessage());
        }
        Integer roleId = roleIds.get(0);
        // 验证权限
        Map<Object, Object> permissions = redisService.hmget(PERSISTENCE_NAME);
        boolean flag = false;
        Iterator<Map.Entry<Object, Object>> iterator1 = permissions.entrySet().iterator();
        while (iterator1.hasNext()) {
            Map.Entry<Object, Object> next = iterator1.next();
            Object key1 = next.getKey();
            if (String.valueOf(roleId).equals(key1)) {
                List<PermissionVo> permissionVoList = Convert.toList(PermissionVo.class, permissions.get(key1));
                for (PermissionVo permissionVo : permissionVoList) {
                    if (path.contains(permissionVo.getUrl())) {
                        flag = true;
                        break;
                    }
                }
            }

        }
        if (!flag) {
            return setResponseInfo(exchange.getResponse(), FORBIDDEN.getCode(), FORBIDDEN.getMessage());
        }
        return chain.filter(exchange);
    }

    /**
     * 获取 token
     *
     * @param request
     * @return
     */
    private String getToken(ServerHttpRequest request) {
        String authorization = request.getHeaders().getFirst(AuthConstants.Authorization);
        if (!StringUtils.hasText(authorization)) {
            return null;
        }
        int index = authorization.indexOf(AuthConstants.Bearer);
        // 未找到
        if (index == -1) {
            return null;
        }
        String token = authorization.substring(index + 7).trim();
        return token;
    }


    private Mono<Void> setResponseInfo(ServerHttpResponse response, Integer code, String msg) {
        JSONObject message = new JSONObject();
        message.put("message", code);
        message.put("code", msg);
        byte[] bits = message.toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "text/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }


    @Override
    public int getOrder() {
        return 0;
    }


    private void getRequiresPermissions(String key) {
        Map<Object, Object> permissionUrlsMap = redisService.hmget(key);
        Iterator<Map.Entry<Object, Object>> iterator = permissionUrlsMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Object, Object> next = iterator.next();
            PermissionEntityVO convert = Convert.convert(PermissionEntityVO.class, next.getValue());
            System.out.println(convert.getPermission());
        }
    }


}
