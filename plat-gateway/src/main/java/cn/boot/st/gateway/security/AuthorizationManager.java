package cn.boot.st.gateway.security;

import cn.boot.common.framework.constant.AuthConstants;
import cn.boot.common.framework.dataobject.dto.PermissionVo;
import cn.boot.st.redis.RedisService;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-18
 **/

/**
 * 鉴权管理器
 */
@Component
@AllArgsConstructor
@Slf4j
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Autowired
    private RedisService redisService;
    private static final String PERSISTENCE_NAME = "PERSISTENCE_NAME";


    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        String path = request.getURI().getPath();
        PathMatcher pathMatcher = new AntPathMatcher();
        if (request.getURI().getPath().contains("/v2/api-docs")) {
            //放行
            return Mono.just(new AuthorizationDecision(true));
        }

        // 1. 对应跨域的预检请求直接放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(true));
        }


        // 2. token为空拒绝访问
        String token = request.getHeaders().getFirst(AuthConstants.JWT_TOKEN_HEADER);
        if (StrUtil.isBlank(token)) {
            return Mono.just(new AuthorizationDecision(false));
        }
        // 3.缓存取资源权限角色关系列表
        // 4.请求路径匹配到的资源需要的角色权限集合authorities
        Mono<AuthorizationDecision> authorizationDecisionMono = mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(roleId -> {
                    // 5. roleId是请求用户的角色(格式:ROLE_{roleId})，是请求资源所需要角色的集合
                    log.info("访问路径：{}", path);
                    log.info("用户角色roleId：{}", roleId);
                    return isPermitted(roleId, path);
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
        return authorizationDecisionMono;
    }


    private boolean isPermitted(String roleId, String path) {
        // 验证权限
        Map<Object, Object> permissions = redisService.hmget(PERSISTENCE_NAME);
        boolean containsKey = permissions.containsKey(roleId);
        if (!containsKey) {
            return false;
        }
        Object obj = permissions.get(roleId);
        if (obj instanceof ArrayList<?>) {
            for (Object o : (List<?>) obj) {
                String url = PermissionVo.class.cast(o).getUrl();
                if (path.contains(url)) {
                    return true;
                }
            }
        }
        return false;

    }
}
