package com.mht.rocketmq.service;

import com.mht.rocketmq.model.AccountInfo;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-10-22 16:05
 **/
public interface UserService {

    AccountInfo getAccountInfo(Long id);

}
