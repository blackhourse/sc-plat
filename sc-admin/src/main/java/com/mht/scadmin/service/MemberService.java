package com.mht.scadmin.service;

import com.mht.core.domain.UserDto;
import com.mht.scmbg.model.UmsMember;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-11-11 16:41
 **/
public interface MemberService {
    /**
     * 获取用户信息
     */
    UserDto loadUserByUsername(String username);

    /**
     * 根据用户名获取会员
     */
    UmsMember getByUsername(String username);


}
