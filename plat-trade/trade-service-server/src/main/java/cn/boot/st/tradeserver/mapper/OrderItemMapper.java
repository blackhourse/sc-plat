package cn.boot.st.tradeserver.mapper;

import cn.boot.st.tradeserver.dataobject.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-12
 **/

@Repository
public interface OrderItemMapper extends BaseMapper<OrderItem> {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertOrUpdate(OrderItem record);

    int insertOrUpdateSelective(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

    int updateBatch(List<OrderItem> list);

    int batchInsert(@Param("list") List<OrderItem> list);
}