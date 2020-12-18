package cn.boot.st.managementweb.interceptor;


import cn.boot.common.framework.dataobject.dto.OAuth2AccessTokenRespDTO;
import cn.boot.common.framework.enums.UserTypeEnum;
import cn.boot.common.framework.exception.util.GlobalException;
import cn.boot.common.framework.exception.util.ServiceException;
import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.common.framework.util.HttpUtil;
import cn.boot.st.managementweb.dataobject.bo.PermissionCheckBO;
import cn.boot.st.managementweb.controller.permission.vo.RoleResourceVo;
import cn.boot.st.managementweb.remote.AuthFeignService;
import cn.boot.st.managementweb.service.admin.AdminService;
import cn.boot.st.managementweb.service.permission.PermissionService;
import cn.boot.st.security.annotations.RequiresNone;
import cn.boot.st.security.annotations.RequiresPermissions;
import cn.boot.st.security.core.context.AdminSecurityContext;
import cn.boot.st.security.core.context.AdminSecurityContextHolder;
import cn.boot.st.web.util.CommonWebUtil;
import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static cn.boot.common.framework.constant.SystemErrorCodeConstants.OAUTH2_ACCESS_TOKEN_NOT_FOUND;
import static cn.boot.common.framework.constant.SystemErrorCodeConstants.OAUTH2_ACCESS_TOKEN_TOKEN_EXPIRED;
import static cn.boot.common.framework.exception.enums.GlobalErrorCodeConstants.FORBIDDEN;
import static cn.boot.common.framework.exception.enums.GlobalErrorCodeConstants.UNAUTHORIZED;

/**
 * @author Administrator
 */
@Slf4j
public class AdminSecurityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AuthFeignService authFeignService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 获得访问令牌
        Integer adminId = obtainAdminId(request);
        // 校验认证
        checkAuthentication((HandlerMethod) handler, adminId);
        // 校验权限
        checkPermission((HandlerMethod) handler, adminId);
        return true;
    }

    /**
     * 获得访问令牌
     *
     * @param request
     * @return
     */
    private Integer obtainAdminId(HttpServletRequest request) {
        String accessToken = HttpUtil.obtainAuthorization(request);
        Integer adminId = null;
        if (accessToken != null) {
            OAuth2AccessTokenRespDTO auth2AccessTokenRespDTO = checkAccessToken(accessToken);
           /* if (!UserTypeEnum.ADMIN.getValue().equals(auth2AccessTokenRespDTO.getUserType())) {
                throw ServiceExceptionUtil.exception(OAUTH_USER_TYPE_ERROR);
            }*/
            // 获得用户编号
            adminId = auth2AccessTokenRespDTO.getUserId();
            // 设置到 Request 中
            CommonWebUtil.setUserId(request, adminId);
            CommonWebUtil.setUserType(request, UserTypeEnum.ADMIN.getValue());
            // 设置到
            AdminSecurityContext adminSecurityContext = new AdminSecurityContext().setAdminId(adminId);
            AdminSecurityContextHolder.setContext(adminSecurityContext);
        }
        return adminId;
    }

    /**
     * 检查token
     *
     * @param accessToken
     * @return OAuth2AccessTokenRespDTO
     */
    public OAuth2AccessTokenRespDTO checkAccessToken(String accessToken) {
        OAuth2AccessTokenRespDTO oAuth2AccessToken = getOAuth2AccessToken(accessToken);
        // 不存在
        if (oAuth2AccessToken == null) {
            throw ServiceExceptionUtil.exception(OAUTH2_ACCESS_TOKEN_NOT_FOUND);
        }
        // 已过期
        if (oAuth2AccessToken.getExpiresTime().getTime() < System.currentTimeMillis()) {
            throw ServiceExceptionUtil.exception(OAUTH2_ACCESS_TOKEN_TOKEN_EXPIRED);
        }
        return oAuth2AccessToken;
    }

    /**
     * 获取token
     *
     * @param accessToken
     * @return OAuth2AccessTokenRespDTO
     */
    private OAuth2AccessTokenRespDTO getOAuth2AccessToken(String accessToken) {
        // todo 优先从 Redis 中获取
        // 获取不到，从security-server 中获取
        OAuth2AccessTokenRespDTO auth2AccessTokenRespDTO = authFeignService.getInfoById(accessToken);
        // 如果在 MySQL 存在，则往 Redis 中写入
        if (auth2AccessTokenRespDTO != null) {
            // todo 写入redis
        }
        return auth2AccessTokenRespDTO;
    }

    /**
     * @param handlerMethod
     * @param adminId
     */
    private void checkAuthentication(HandlerMethod handlerMethod, Integer adminId) {
        // 对于 ADMIN 来说，默认需登录
        boolean requiresAuthenticate = !handlerMethod.hasMethodAnnotation(RequiresNone.class);
        if (requiresAuthenticate && adminId == null) {
            throw new GlobalException(UNAUTHORIZED);
        }
    }


    private void checkPermission(HandlerMethod handlerMethod, Integer adminId) {
        RequiresPermissions requiresPermissions = handlerMethod.getMethodAnnotation(RequiresPermissions.class);
        if (requiresPermissions == null) {
            return;
        }
        String[] permissions = requiresPermissions.value();
        if (permissions == null || permissions.length == 0) {
            return;
        }
        // 权限验证
        checkPermission(new PermissionCheckBO().setAdminId(adminId).setPermissions(Arrays.asList(permissions)));
    }

    /**
     * 校验管理员是否拥有指定权限。
     * <p>
     * 如果没有，则抛出 {@link ServiceException} 异常
     *
     * @param checkBO 校验权限 BO
     */
    public void checkPermission(PermissionCheckBO checkBO) {
        // 查询管理员拥有的角色关联数据
        Set<Integer> roleIds = permissionService.listAdminRoles(checkBO.getAdminId());
        // 如果没有角色，默认无法访问
        if (CollectionUtil.isEmpty(roleIds)) {
            throw new GlobalException(FORBIDDEN);
        }
        // 判断是否为超管。若是超管，默认有所有权限
        Boolean isAdmin = adminService.hasSuperAdmin(roleIds);
        if (Boolean.TRUE.equals(isAdmin)) {
            return;
        }
        // 校验权限
        checkPermission(roleIds, checkBO.getPermissions());
    }

    public void checkPermission(Collection<Integer> roleIds, Collection<String> permissions) {
        // 查询权限对应资源
        Set<Integer> permissionIds = permissionService.selectListByPermissions(permissions);
        // 无对应资源，则认为无需权限验证
        if (CollectionUtil.isEmpty(permissionIds)) {
            log.warn("[checkPermission][permission({}) 未配置对应资源]", permissions);
            return;
        }
        // 权限验证
        List<RoleResourceVo> roleResourceVos = permissionService.selectListByResourceIds(permissionIds);
        // 资源未授予任何角色，必然权限验证不通过
        if (CollectionUtil.isEmpty(roleResourceVos)) {
            log.warn("[checkPermission][permissionIds({}) 资源未授予任何角色]", permissionIds);
            throw new GlobalException(FORBIDDEN);
        }
        Map<Integer, List<Integer>> resourceRoleMap = roleResourceVos.stream().collect(Collectors.groupingBy(RoleResourceVo::getResourceId,
                Collectors.mapping(RoleResourceVo::getRoleId, Collectors.toList())));
        for (Map.Entry<Integer, List<Integer>> entry : resourceRoleMap.entrySet()) {
            // 所以有任一不满足，就验证失败，抛出异常
            if (!CollectionUtil.containsAny(roleIds, entry.getValue())) {
                throw new GlobalException(FORBIDDEN);
            }
        }
    }


}
