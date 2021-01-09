package cn.boot.st.tradeserver.mapper;

import cn.boot.st.tradeserver.dataobject.CartItem;
import cn.boot.st.tradeservice.service.cart.dto.CartQueryDto;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname CartItemMapper
 * @Description
 * @Date 2021/1/9
 * @Created by maht
 */
@Repository
public interface CartItemMapper extends BaseMapper<CartItem> {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(CartItem record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(CartItem record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    CartItem selectByPrimaryKey(Integer id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(CartItem record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(CartItem record);


    default CartItem selectOneBySkuIdAndUserId(Integer userId, Integer skuId) {
        return selectOne(
                new LambdaQueryWrapper<CartItem>().eq(CartItem::getUserId, userId)
                        .eq(CartItem::getSkuId, skuId)
        );
    }

    default List<CartItem> selectList(CartQueryDto cartQueryDto) {
        return selectList(new LambdaQueryWrapper<CartItem>()
                .eq(CartItem::getUserId, cartQueryDto.getUserId())
                .eq(cartQueryDto.getSelected() != null, CartItem::getSelected, cartQueryDto.getSelected())
        );
    }
}