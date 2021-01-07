package cn.boot.st.tradeserver.mapper;

import cn.boot.st.tradeserver.dataobject.CartItem;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-07
 **/

@Repository
public interface CartItemMapper extends BaseMapper<CartItem> {
    int updateBatch(List<CartItem> list);

    int updateBatchSelective(List<CartItem> list);

    int batchInsert(@Param("list") List<CartItem> list);

    int insertOrUpdate(CartItem record);

    int insertOrUpdateSelective(CartItem record);

    default List<CartItem> selectList(Integer userId) {
        return selectList(new LambdaQueryWrapper<CartItem>()
                .eq(CartItem::getUserId, userId));
    }

}