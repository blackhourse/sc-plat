package com.sharding.datasource.controller;

import com.sharding.datasource.dataobject.OrderConfigDO;
import com.sharding.datasource.dataobject.OrderDO;
import com.sharding.datasource.dataobject.UserOrderVo;
import com.sharding.datasource.service.OrderConfigService;
import com.sharding.datasource.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-29
 **/
@RestController
public class OrderController {

    @Autowired
    private OrderConfigService configService;
    @Autowired
    private OrderService orderService;

    @GetMapping("config/testSelectById")
    public OrderConfigDO testSelectById(Integer id) {
        return configService.testSelectById(id);
    }

    @GetMapping("order/testSelectById")
    public OrderDO testSelectById(Long id) {
        return orderService.testSelectById(id);
    }

    @GetMapping("order/selectListByUserId")
    public List<OrderDO> selectListByUserId(Integer userId) {
        return orderService.selectListByUserId(userId);
    }

    @GetMapping("order/testInsert")
    public void testInsert(Integer id) {
        orderService.testInsert();
    }


    @GetMapping("order/user-orders")
    public List<UserOrderVo> testInsert() {
        return orderService.selectUserOrderInfo();
    }


}
