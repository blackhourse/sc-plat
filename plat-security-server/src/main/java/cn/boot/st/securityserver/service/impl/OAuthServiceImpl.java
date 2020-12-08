package cn.boot.st.securityserver.service.impl;

import cn.boot.common.framework.dataobject.dto.OAuth2AccessTokenRespDTO;
import cn.boot.common.framework.dataobject.dto.OAuth2CreateAccessTokenReqDTO;
import cn.boot.common.framework.dataobject.vo.PassportAccessTokenVO;
import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.common.framework.util.StringUtils;
import cn.boot.st.securityserver.config.SystemBizProperties;
import cn.boot.st.securityserver.convert.OAuth2Convert;
import cn.boot.st.securityserver.dataobject.domain.OAuth2AccessTokenDO;
import cn.boot.st.securityserver.dataobject.domain.OAuth2RefreshTokenDO;
import cn.boot.st.securityserver.mapper.OAuth2AccessTokenMapper;
import cn.boot.st.securityserver.mapper.OAuth2RefreshTokenMapper;
import cn.boot.st.securityserver.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static cn.boot.common.framework.constant.SystemErrorCodeConstants.OAUTH2_REFRESH_TOKEN_NOT_FOUND;
import static cn.boot.common.framework.constant.SystemErrorCodeConstants.OAUTH_REFRESH_TOKEN_EXPIRED;

/**
 * @Classname OAuthServiceImpl
 * @Description
 * @Date 2020/12/7
 * @Created by maht
 */
@Service
public class OAuthServiceImpl implements OAuthService {

    @Autowired
    private OAuth2RefreshTokenMapper oauth2RefreshTokenMapper;

    @Autowired
    private OAuth2AccessTokenMapper oauth2AccessTokenMapper;

    @Autowired
    private SystemBizProperties systemBizProperties;


    @Override
    public OAuth2AccessTokenRespDTO createAccessToken(OAuth2CreateAccessTokenReqDTO auth2CreateAccessTokenReqDTO) {

        // 创建刷新令牌 + 访问令牌
        OAuth2RefreshTokenDO refreshTokenDO = this.createOAuth2RefreshToken(auth2CreateAccessTokenReqDTO.getUserId(), auth2CreateAccessTokenReqDTO.getUserType(), auth2CreateAccessTokenReqDTO.getCreateIp());
        OAuth2AccessTokenDO oAuth2AccessToken = createOAuth2AccessToken(refreshTokenDO, auth2CreateAccessTokenReqDTO.getCreateIp());
        // 返回访问令牌
        return OAuth2Convert.INSTANCE.convert(oAuth2AccessToken);
    }

    @Override
    public PassportAccessTokenVO refreshToken(String refreshToken, String ip) {
        OAuth2RefreshTokenDO refreshTokenDO = oauth2RefreshTokenMapper.selectById(refreshToken);
        // 校验刷新令牌是否合法
        // 不存在
        if (refreshTokenDO == null) {
            throw ServiceExceptionUtil.exception(OAUTH2_REFRESH_TOKEN_NOT_FOUND);
        }
        // 已过期
        if (refreshTokenDO.getExpiresTime().getTime() < System.currentTimeMillis()) {
            throw ServiceExceptionUtil.exception(OAUTH_REFRESH_TOKEN_EXPIRED);
        }
        // 标记 refreshToken 对应的 accessToken 都不合法
        // 这块的实现，参考了 Spring Security OAuth2 的代码
        oauth2AccessTokenMapper.deleteByRefreshToken(refreshToken);
        // 创建访问令牌
        OAuth2AccessTokenDO oauth2AccessTokenDO = createOAuth2AccessToken(refreshTokenDO, ip);
        // 返回访问令牌
        return OAuth2Convert.INSTANCE.convertPassToken(oauth2AccessTokenDO);
    }


    private OAuth2AccessTokenDO createOAuth2AccessToken(OAuth2RefreshTokenDO refreshTokenDO, String createIp) {
        OAuth2AccessTokenDO accessToken = new OAuth2AccessTokenDO()
                .setId(generateAccessToken())
                .setUserId(refreshTokenDO.getUserId()).setUserType(refreshTokenDO.getUserType())
                .setRefreshToken(refreshTokenDO.getId())
                .setExpiresTime(new Date(System.currentTimeMillis() + systemBizProperties.getAccessTokenExpireTimeMillis()))
                .setCreateIp(createIp);
        oauth2AccessTokenMapper.insert(accessToken);
        return accessToken;
    }


    private OAuth2RefreshTokenDO createOAuth2RefreshToken(Integer userId, Integer userType, String createIp) {
        OAuth2RefreshTokenDO refreshToken = new OAuth2RefreshTokenDO()
                .setId(generateRefreshToken())
                .setUserId(userId).setUserType(userType)
                .setExpiresTime(new Date(System.currentTimeMillis() + systemBizProperties.getRefreshTokenExpireTimeMillis()))
                .setCreateIp(createIp);
        oauth2RefreshTokenMapper.insert(refreshToken);
        return refreshToken;
    }


    private static String generateAccessToken() {
        return StringUtils.uuid(true);
    }

    private static String generateRefreshToken() {
        return StringUtils.uuid(true);
    }

}
