package cn.boot.st.productserver.convert;


import cn.boot.st.productserver.dataobject.ProductSku;
import cn.boot.st.productservice.bo.ProductSkuCreateOrUpdateBO;
import cn.boot.st.productservice.vo.sku.ProductSkuRespVo;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
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

    @Mappings({
            @Mapping(source = "attrs", target = "attrValueIds", qualifiedByName = "translateAttrValueIdsFromString"),
            @Mapping(target = "attrs", ignore = true),
    })
    ProductSkuRespVo convert(ProductSku productSku);

    @Named("translateAttrValueIdsFromString")
    default List<Integer> translateAttrValueIdsFromString(String attrValueIdsStar) {
        String[] stringArray = org.springframework.util.StringUtils.tokenizeToStringArray(attrValueIdsStar, ",");
        List<Integer> array = new ArrayList<>(stringArray.length);
        for (String string : stringArray) {
            array.add(Integer.valueOf(string));
        }
        return array;
    }

    @Named("translateAttrValueIdsToString")
    default String translateAttrValueIdsToString(List<String> attrValueIdsStar) {
        return StringUtils.join(attrValueIdsStar, ",");
    }

}
