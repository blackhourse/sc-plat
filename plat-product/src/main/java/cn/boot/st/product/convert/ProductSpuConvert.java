package cn.boot.st.product.convert;

import cn.boot.st.product.controller.spu.dto.ProductSpuCreateDTO;
import cn.boot.st.product.dataobject.bo.ProductSkuCreateOrUpdateBO;
import cn.boot.st.product.dataobject.domain.ProductSpu;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-18
 **/
@Mapper
public interface ProductSpuConvert {

    ProductSpuConvert INSTANCE = Mappers.getMapper(ProductSpuConvert.class);

    List<ProductSkuCreateOrUpdateBO> convert(List<ProductSpuCreateDTO.Sku> skus);

    @Mapping(source = "picUrls", target = "picUrls", qualifiedByName = "translatePicUrlsFromStringList")
    ProductSpu convert(ProductSpuCreateDTO bean);


    @Named("translatePicUrlsFromStringList")
    default String translatePicUrlsFromList(List<String> picUrls) {
        return StringUtils.join(picUrls, ",");
    }

}
