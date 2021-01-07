package cn.boot.st.tradeserver.service.cart;

import cn.boot.st.tradeserver.mapper.CartItemMapper;
import cn.boot.st.tradeserver.remote.ProductFeignService;
import cn.boot.st.tradeservice.service.cart.CartService;
import cn.boot.st.tradeservice.service.cart.dto.CartAddDto;
import cn.boot.st.tradeservice.service.cart.dto.CartQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-07
 **/
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private ProductFeignService productFeignService;

    @Override
    public String addCart(CartAddDto cartAddDto) {
        //1. // 校验商品 SKU 是否合法
//        CommonResult<ProductSpuRespVO> productSpuRespVO = productFeignService.getProductBySkuId(cartAddDto.getSkuId());
//        productSpuRespVO.checkError();
        //
        return "success";
    }

    @Override
    public String cartQuery(CartQueryDto cartQueryDto) {
        cartItemMapper.selectList(cartQueryDto.getUserId());
        return "123";
    }
}
