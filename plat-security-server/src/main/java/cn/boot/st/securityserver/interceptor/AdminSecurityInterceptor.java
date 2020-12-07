package cn.boot.st.securityserver.interceptor;


import cn.boot.common.framework.enums.UserTypeEnum;
import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.common.framework.util.HttpUtil;
import cn.boot.st.security.core.context.AdminSecurityContext;
import cn.boot.st.security.core.context.AdminSecurityContextHolder;
import cn.boot.st.security.dto.OAuth2AccessTokenRespDTO;
import cn.boot.st.securityserver.config.SystemBizProperties;
import cn.boot.st.securityserver.convert.OAuth2Convert;
import cn.boot.st.securityserver.dataobject.oauth.OAuth2AccessTokenDO;
import cn.boot.st.securityserver.mapper.OAuth2AccessTokenMapper;
import cn.boot.st.securityserver.mapper.OAuth2RefreshTokenMapper;
import cn.boot.st.web.util.CommonWebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.boot.common.framework.constant.SystemErrorCodeConstants.*;

public class AdminSecurityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SystemBizProperties systemBizProperties;

    @Autowired
    private OAuth2AccessTokenMapper oauth2AccessTokenMapper;
    @Autowired
    private OAuth2RefreshTokenMapper oauth2RefreshTokenMapper;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 获得访问令牌
        Integer adminId = this.obtainAdminId(request);
        // 校验认证
        // 校验权限
        return true;
    }

    private Integer obtainAdminId(HttpServletRequest request) {
        String accessToken = HttpUtil.obtainAuthorization(request);
        Integer adminId = null;
        if (accessToken != null) {
            OAuth2AccessTokenRespDTO oAuth2AccessTokenRespDTO = checkAccessToken(accessToken);
            checkAccessToken(accessToken);
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

    @Transactional
    public OAuth2AccessTokenRespDTO checkAccessToken(String accessToken) {
        OAuth2AccessTokenDO accessTokenDO = this.getOAuth2AccessToken(accessToken);
        if (accessTokenDO == null) { // 不存在
            throw ServiceExceptionUtil.exception(OAUTH2_ACCESS_TOKEN_NOT_FOUND);
        }
        if (accessTokenDO.getExpiresTime().getTime() < System.currentTimeMillis()) { // 已过期
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
