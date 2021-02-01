package com.sharding.datasource.mapper;

import com.sharding.datasource.dataobject.OrderDO;
import com.sharding.datasource.dataobject.UserOrderVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    OrderDO selectById(@Param("id") Integer id);

    List<OrderDO> selectListByUserId(@Param("userId") Integer userId);

    void insert(OrderDO order);

    List<UserOrderVo> selectUserOrderInfo();

}
