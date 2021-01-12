package cn.boot.st.tradeservice.service.cart;

import cn.boot.st.tradeservice.service.dto.CartAddDto;
import cn.boot.st.tradeservice.service.dto.CartQueryDto;
import cn.boot.st.tradeservice.service.dto.CartUpdateDto;
import cn.boot.st.tradeservice.service.dto.CartUpdateSelectDto;
import cn.boot.st.tradeservice.service.vo.CartInfoVo;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-06
 **/
public interface CartService {

    /**
     * 添加购物车
     *
     * @param cartAddDto
     * @return
     */
    Integer addCart(CartAddDto cartAddDto);

    /**
     * 更新购物车商品数量
     *
     * @param cartUpdateDto
     */
    void updateCartItemQuantity(CartUpdateDto cartUpdateDto);

    /**
     * 获取购物车商品数量
     *
     * @param userId
     * @return
     */
    Integer sumCartItemQuantity(Integer userId);

    /**
     * 获取购物车商品列表
     *
     * @param cartQueryDto
     * @return
     */
    CartInfoVo getCartDetail(CartQueryDto cartQueryDto);

    /**
     * 更细购物车商品 选中情况
     *
     * @param cartUpdateSelectDto
     */
    void updateCartItemSelected(CartUpdateSelectDto cartUpdateSelectDto);


}
