package cn.boot.st.managementweb.service.admin;

import cn.boot.common.framework.dataobject.vo.AdminUserTokenInfo;
import cn.boot.st.managementweb.dataobject.domain.AdminDO;
import cn.boot.st.managementweb.dataobject.domain.AdminRoleDO;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-29
 **/
public interface AdminCacheService {

    public void setAuthToken(String token, AdminUserTokenInfo adminUserTokenInfo);

}
