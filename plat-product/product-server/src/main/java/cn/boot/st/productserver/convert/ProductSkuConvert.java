package cn.boot.st.productserver.convert;


import cn.boot.st.productserver.dataobject.ProductSku;
import cn.boot.st.productservice.bo.ProductSkuCreateOrUpdateBO;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
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
