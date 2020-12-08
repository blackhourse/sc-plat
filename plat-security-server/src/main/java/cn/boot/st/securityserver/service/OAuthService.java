package cn.boot.st.securityserver.service;

import cn.boot.common.framework.dataobject.dto.OAuth2AccessTokenRespDTO;
import cn.boot.common.framework.dataobject.dto.OAuth2CreateAccessTokenReqDTO;
import cn.boot.common.framework.dataobject.vo.PassportAccessTokenVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Classname OAuthService
 * @Description
 * @Date 2020/12/7
 * @Created by maht
 */
public interface OAuthService {


    /**
     * 创建token
     *
     * @param auth2CreateAccessTokenReqDTO
     * @return
     */
    OAuth2AccessTokenRespDTO createAccessToken(OAuth2CreateAccessTokenReqDTO auth2CreateAccessTokenReqDTO);

    /**
     * 刷新token
     *
     * @param refreshToken
     * @param ip
     * @return
     */
    PassportAccessTokenVO refreshToken(@RequestParam("refreshToken") String refreshToken, @Param("ip") String ip);

}
