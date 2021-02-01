package com.sharding.datasource.mapper;

import com.sharding.datasource.dataobject.OrderConfigDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderConfigMapper {

    OrderConfigDO selectById(@Param("id") Integer id);

}
