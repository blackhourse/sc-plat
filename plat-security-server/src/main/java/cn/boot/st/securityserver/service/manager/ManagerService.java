package cn.boot.st.securityserver.service.manager;

import cn.boot.common.framework.dataobject.dto.OAuth2AccessTokenRespDTO;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-10
 **/
public interface ManagerService {

    /**
     * 根据id获取token详情
     *
     * @param token
     * @return
     */
    OAuth2AccessTokenRespDTO getInfoById(String token);

}
