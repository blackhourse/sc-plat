package cn.boot.st.tradeserver.convert;

import cn.boot.st.productservice.bo.ProductAttrKeyValueBO;
import cn.boot.st.productservice.vo.sku.ProductSkuRespVo;
import cn.boot.st.productservice.vo.spu.ProductSpuRespVO;
import cn.boot.st.tradeservice.service.order.vo.OrderConfirmCreateRespVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Classname OrderConvert
 * @Description
 * @Date 2021/1/8
 * @Created by maht
 */
@Mapper
public interface OrderConvert {
    OrderConvert INSTANCE = Mappers.getMapper(OrderConvert.class);

    OrderConfirmCreateRespVo.Spu convert(ProductSpuRespVO spu);

    @Mapping(target = "attrValueIds", ignore = true)
    OrderConfirmCreateRespVo.Sku convert(ProductSkuRespVo sku);

    List<OrderConfirmCreateRespVo.AttrValueInfo> convert(List<ProductAttrKeyValueBO> attrKeyValueBOS);

}
