package com.sharding.datasource.service.impl;

import com.sharding.datasource.dataobject.OrderDO;
import com.sharding.datasource.dataobject.UserOrderVo;
import com.sharding.datasource.mapper.OrderMapper;
import com.sharding.datasource.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-29
 **/
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;


    @Override
    public OrderDO testSelectById(Long id) {
        return orderMapper.selectById(1);
    }

    @Override
    public List<OrderDO> selectListByUserId(Integer userId) {
        return orderMapper.selectListByUserId(userId);
    }

    @Override
    public void testInsert() {
        for (int i = 1; i < 10; i++) {
            OrderDO order = new OrderDO();
            order.setUserId(i);
            orderMapper.insert(order);
        }
    }

    @Override
    public List<UserOrderVo> selectUserOrderInfo() {
        return orderMapper.selectUserOrderInfo();
    }
}
