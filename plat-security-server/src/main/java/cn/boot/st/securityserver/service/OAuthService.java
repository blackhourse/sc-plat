package cn.boot.st.securityserver.service;

import cn.boot.st.security.dto.OAuth2AccessTokenRespDTO;
import cn.boot.st.security.dto.OAuth2CreateAccessTokenReqDTO;

/**
 * @Classname OAuthService
 * @Description
 * @Date 2020/12/7
 * @Created by maht
 */
public interface OAuthService {


    OAuth2AccessTokenRespDTO createAccessToken(OAuth2CreateAccessTokenReqDTO auth2CreateAccessTokenReqDTO);

}
