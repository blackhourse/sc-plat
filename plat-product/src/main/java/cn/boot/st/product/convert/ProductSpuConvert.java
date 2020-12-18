package cn.boot.st.product.convert;

import cn.boot.st.product.controller.spu.dto.ProductSpuCreateDTO;
import cn.boot.st.product.dataobject.bo.ProductSkuCreateOrUpdateBO;
import cn.boot.st.product.dataobject.domain.ProductSku;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-18
 **/
public interface ProductSpuConvert {

    ProductSpuConvert INSTANCE = Mappers.getMapper(ProductSpuConvert.class);

    List<ProductSkuCreateOrUpdateBO> convert(List<ProductSpuCreateDTO.Sku> skus);

}
