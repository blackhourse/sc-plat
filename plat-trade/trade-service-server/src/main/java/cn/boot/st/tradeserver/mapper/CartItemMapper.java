package cn.boot.st.tradeserver.mapper;

import cn.boot.st.tradeserver.dataobject.CartItem;
import cn.boot.st.tradeserver.enums.CartItemStatusEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

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

    default List<CartItem> selectList(Integer userId, Boolean selected) {
        return selectList(new LambdaQueryWrapper<CartItem>()
                .eq(CartItem::getUserId, userId)
                .eq(selected != null, CartItem::getSelected, selected)
        );
    }

    default Integer selectSumProductByUserId(Integer userId) {
        // SQL sum 查询
        return selectCount(
                new LambdaQueryWrapper<CartItem>().eq(CartItem::getUserId, userId)
                        .eq(CartItem::getStatus, CartItemStatusEnum.NORMAL.getValue())
        );
    }

    default List<CartItem> selectListByUserIdAndCartItemIds(Integer userId, Set<Integer> cartItemIds) {
        return selectList(new LambdaQueryWrapper<CartItem>().eq(CartItem::getUserId, userId)
                .eq(CartItem::getStatus, CartItemStatusEnum.NORMAL.getValue())
                .in(CartItem::getId, cartItemIds)
        );
    }

    default void updateByIdsAndUserId(Set<Integer> ids, Integer userId, Boolean selected) {
        update(null, new LambdaUpdateWrapper<CartItem>().in(CartItem::getId, ids)
                .eq(CartItem::getUserId, userId)
                .set(CartItem::getSelected, selected));
    }


}