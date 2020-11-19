package com.mht.sc.scadmin.service;

public interface SysUserCacheService {

    /**
     * 设置验证码
     */
    void setAuthCode(String telephone, String authCode);

    /**
     * 获取验证码
     */
    String getAuthCode(String telephone);

    /**
     * 设置token 有效时间
     *
     * @param key
     */
    void setSysUserToken(String key, String id);

}
