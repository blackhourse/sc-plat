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

}
