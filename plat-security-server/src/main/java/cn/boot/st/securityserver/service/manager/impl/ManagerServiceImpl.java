package cn.boot.st.securityserver.service.manager.impl;

import cn.boot.common.framework.dataobject.dto.OAuth2AccessTokenRespDTO;
import cn.boot.st.securityserver.convert.OAuth2Convert;
import cn.boot.st.securityserver.dataobject.domain.OAuth2AccessTokenDO;
import cn.boot.st.securityserver.mapper.OAuth2AccessTokenMapper;
import cn.boot.st.securityserver.service.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-10
 **/
@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private OAuth2AccessTokenMapper oauth2AccessTokenMapper;

    @Override
    public OAuth2AccessTokenRespDTO getInfoById(String token) {
        OAuth2AccessTokenDO oAuth2AccessTokenDO = oauth2AccessTokenMapper.selectById(token);
        return OAuth2Convert.INSTANCE.convert(oAuth2AccessTokenDO);
    }
}
