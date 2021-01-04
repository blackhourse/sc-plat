package cn.boot.st.managementweb.service.api;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-31
 **/
public interface ApiService {

    /**
     * 获取用户角色信息
     * @param userId
     * @return
     */
    List<Integer> getUserRoleInfo(Integer userId);

}
