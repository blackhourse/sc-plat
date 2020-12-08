package cn.boot.st.securityserver.interceptor;


import cn.boot.common.framework.dataobject.dto.OAuth2AccessTokenRespDTO;
import cn.boot.common.framework.enums.UserTypeEnum;
import cn.boot.common.framework.exception.util.GlobalException;
import cn.boot.common.framework.exception.util.ServiceException;
import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.common.framework.util.HttpUtil;
import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.security.annotations.RequiresNone;
import cn.boot.st.security.annotations.RequiresPermissions;
import cn.boot.st.security.core.context.AdminSecurityContext;
import cn.boot.st.security.core.context.AdminSecurityContextHolder;
import cn.boot.st.securityserver.config.SystemBizProperties;
import cn.boot.st.securityserver.convert.OAuth2Convert;
import cn.boot.st.securityserver.dataobject.domain.OAuth2AccessTokenDO;
import cn.boot.st.securityserver.dataobject.dto.PermissionCheckDTO;
import cn.boot.st.securityserver.feign.PermissionFeignService;
import cn.boot.st.securityserver.mapper.OAuth2AccessTokenMapper;
import cn.boot.st.securityserver.mapper.OAuth2RefreshTokenMapper;
import cn.boot.st.web.util.CommonWebUtil;
import cn.hutool.core.collection.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Set;

import static cn.boot.common.framework.constant.SystemErrorCodeConstants.*;
import static cn.boot.common.framework.exception.enums.GlobalErrorCodeConstants.FORBIDDEN;
import static cn.boot.common.framework.exception.enums.GlobalErrorCodeConstants.UNAUTHORIZED;

public class AdminSecurityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SystemBizProperties systemBizProperties;

    @Autowired
    private OAuth2AccessTokenMapper oauth2AccessTokenMapper;
    @Autowired
    private OAuth2RefreshTokenMapper oauth2RefreshTokenMapper;

    @Autowired
    private PermissionFeignService permissionFeignService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 获得访问令牌
        Integer adminId = this.obtainAdminId(request);
        // 校验认证
        checkAuthentication((HandlerMethod) handler, adminId);
        // 校验权限
        checkPermission((HandlerMethod) handler, adminId);
        return true;
    }

    private Integer obtainAdminId(HttpServletRequest request) {
        String accessToken = HttpUtil.obtainAuthorization(request);
        Integer adminId = null;
        if (accessToken != null) {
            OAuth2AccessTokenRespDTO oAuth2AccessTokenRespDTO = checkAccessToken(accessToken);
            // 校验用户类型正确
            if (!UserTypeEnum.ADMIN.getValue().equals(oAuth2AccessTokenRespDTO.getUserType())) {
                throw ServiceExceptionUtil.exception(OAUTH_USER_TYPE_ERROR);
            }
            // 获得用户编号
            adminId = oAuth2AccessTokenRespDTO.getUserId();
            // 设置到 Request 中
            CommonWebUtil.setUserId(request, adminId);
            CommonWebUtil.setUserType(request, UserTypeEnum.ADMIN.getValue());
            // 设置到
            AdminSecurityContext adminSecurityContext = new AdminSecurityContext().setAdminId(adminId);
            AdminSecurityContextHolder.setContext(adminSecurityContext);
        }
        return adminId;
    }


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
        checkPermission(new PermissionCheckDTO().setAdminId(adminId).setPermissions(Arrays.asList(permissions)));
//        permissionRpc.checkPermission(new PermissionCheckDTO().setAdminId(adminId).setPermissions(Arrays.asList(permissions)))
//                .checkError();
    }


    /**
     * 校验管理员是否拥有指定权限。
     * <p>
     * 如果没有，则抛出 {@link ServiceException} 异常
     *
     * @param checkDTO 校验权限 DTO
     */
    public void checkPermission(PermissionCheckDTO checkDTO) {
        // 查询管理员拥有的角色关联数据

        CommonResult<Set<Integer>> setCommonResult = permissionFeignService.listAdminRoles(checkDTO.getAdminId());
        Set<Integer> roleIds = setCommonResult.getData();
        // 如果没有角色，默认无法访问
        if (CollectionUtil.isEmpty(roleIds)) {
            throw new GlobalException(FORBIDDEN);
        }
        // 判断是否为超管。若是超管，默认有所有权限
        CommonResult<Boolean> booleanCommonResult = permissionFeignService.hasSuperAdmin(roleIds);
        if (booleanCommonResult.getData()) {
            return;
        }
        // 校验权限
//        permissionService.checkPermission(roleIds, checkDTO.getPermissions());
    }

    @Transactional
    public OAuth2AccessTokenRespDTO checkAccessToken(String accessToken) {
        OAuth2AccessTokenDO accessTokenDO = this.getOAuth2AccessToken(accessToken);
        // 不存在
        if (accessTokenDO == null) {
            throw ServiceExceptionUtil.exception(OAUTH2_ACCESS_TOKEN_NOT_FOUND);
        }
        // 已过期
        if (accessTokenDO.getExpiresTime().getTime() < System.currentTimeMillis()) {
            throw ServiceExceptionUtil.exception(OAUTH2_ACCESS_TOKEN_TOKEN_EXPIRED);
        }
        // 返回访问令牌
        return OAuth2Convert.INSTANCE.convert(accessTokenDO);
    }

    private OAuth2AccessTokenDO getOAuth2AccessToken(String accessToken) {
        // todo 优先从 Redis 中获取
        // 获取不到，从 MySQL 中获取
        OAuth2AccessTokenDO accessTokenDO = oauth2AccessTokenMapper.selectById(accessToken);
        // 如果在 MySQL 存在，则往 Redis 中写入
        if (accessTokenDO != null) {
            // todo 写入redis
        }
        return accessTokenDO;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 清空 SecurityContext
        AdminSecurityContextHolder.clear();
    }

}
