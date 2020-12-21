package cn.boot.st.product.convert;

import cn.boot.st.product.dataobject.bo.ProductSkuCreateOrUpdateBO;
import cn.boot.st.product.dataobject.domain.ProductSku;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-21
 **/
@Mapper
public interface ProductSkuConvert {
    ProductSkuConvert INSTANCE = Mappers.getMapper(ProductSkuConvert.class);


    List<ProductSku> convertList(List<ProductSkuCreateOrUpdateBO> productSkuCreateOrUpdateBOList);

    @Named("translateAttrValueIdsToString")
    default String translateAttrValueIdsToString(List<String> attrValueIdsStar) {
        return StringUtils.join(attrValueIdsStar, ",");
    }

}
