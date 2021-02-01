package com.sharding.datasource.service;

import com.sharding.datasource.dataobject.OrderDO;
import com.sharding.datasource.dataobject.UserOrderVo;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-29
 **/
public interface OrderService {
    OrderDO testSelectById(Long id);

    List<OrderDO> selectListByUserId(Integer userId);

    void testInsert();

    List<UserOrderVo> selectUserOrderInfo();

}
