package com.sharding.datasource.service.impl;

import com.sharding.datasource.dataobject.OrderConfigDO;
import com.sharding.datasource.mapper.OrderConfigMapper;
import com.sharding.datasource.service.OrderConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-29
 **/
@Service
public class OrderConfigServiceImpl implements OrderConfigService {

    @Autowired
    private OrderConfigMapper orderConfigMapper;

    @Override
    public OrderConfigDO testSelectById(Integer id) {
        OrderConfigDO orderConfigDO = orderConfigMapper.selectById(id);
        System.out.println();
        return orderConfigDO;
    }
}
