package com.mht.rocketmq.service.impl;

import com.mht.rocketmq.mapper.AccountInfoMapper;
import com.mht.rocketmq.model.AccountInfo;
import com.mht.rocketmq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-10-22 16:06
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    AccountInfoMapper accountInfoMapper;


    @Override
    public AccountInfo getAccountInfo(Long id) {
        return accountInfoMapper.selectByPrimaryKey(id);
    }
}
