package cn.boot.st.tradeserver.convert;

import cn.boot.st.productservice.vo.sku.ProductSkuRespVo;
import cn.boot.st.productservice.vo.spu.ProductSpuRespVO;
import cn.boot.st.tradeserver.dataobject.CartItem;
import cn.boot.st.tradeservice.service.cart.bo.CartProductItemBo;
import cn.boot.st.tradeservice.service.cart.dto.CartAddDto;
import cn.boot.st.tradeservice.service.cart.vo.CartInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Classname CartConvert
 * @Description
 * @Date 2021/1/8
 * @Created by maht
 */
@Mapper
public interface CartConvert {
    CartConvert INSTANCE = Mappers.getMapper(CartConvert.class);

    CartItem convert(CartAddDto bean);

    List<CartProductItemBo> convert(List<CartItem> cartItems);


    CartInfoVo.Sku convert(ProductSkuRespVo productSkuRespVo);

    CartInfoVo.Spu convert(ProductSpuRespVO productSpuRespVO);

}
