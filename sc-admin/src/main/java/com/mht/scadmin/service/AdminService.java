package com.mht.scadmin.service;

import com.mht.core.domain.UserDto;
import com.mht.scmbg.model.UmsAdmin;
import com.mht.scmbg.model.UmsRole;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-11-11 15:52
 **/
public interface AdminService {
    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    UserDto loadUserByUsername(String username);

    /**
     * 获取用户对于角色
     */
    List<UmsRole> getRoleList(Long adminId);

    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);


}
