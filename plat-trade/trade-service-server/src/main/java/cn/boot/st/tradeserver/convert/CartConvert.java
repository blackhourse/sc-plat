package cn.boot.st.tradeserver.convert;

import cn.boot.st.productservice.bo.ProductAttrKeyValueBO;
import cn.boot.st.productservice.vo.sku.ProductSkuRespVo;
import cn.boot.st.productservice.vo.spu.ProductSpuRespVO;
import cn.boot.st.tradeserver.dataobject.CartItem;
import cn.boot.st.tradeservice.service.vo.CartProductItemVo;
import cn.boot.st.tradeservice.service.dto.CartAddDto;
import cn.boot.st.tradeservice.service.vo.CartInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

    List<CartProductItemVo> convert(List<CartItem> cartItems);

    @Mapping(target = "attrValueIds", ignore = true)
    CartInfoVo.Sku convert(ProductSkuRespVo productSkuRespVo);

    CartInfoVo.Spu convert(ProductSpuRespVO productSpuRespVO);

    List<CartInfoVo.AttrValueInfo> convertAttrList(List<ProductAttrKeyValueBO> attrKeyValueBOList);

}
