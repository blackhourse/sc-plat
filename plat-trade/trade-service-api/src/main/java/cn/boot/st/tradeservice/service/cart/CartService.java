package cn.boot.st.tradeservice.service.cart;

import cn.boot.st.tradeservice.service.cart.dto.CartAddDto;
import cn.boot.st.tradeservice.service.cart.dto.CartQueryDto;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-06
 **/
public interface CartService {

    /**
     * 添加购物车
     * @param cartAddDto
     * @return
     */
    String addCart(CartAddDto cartAddDto);

    /**
     * 购车车查询
     *
     * @param cartQueryDto
     */
    String cartQuery(CartQueryDto cartQueryDto);

}
