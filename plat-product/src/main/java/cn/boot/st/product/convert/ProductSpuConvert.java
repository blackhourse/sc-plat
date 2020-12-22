package cn.boot.st.product.convert;

import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.product.controller.spu.dto.ProductSpuCreateDTO;
import cn.boot.st.product.controller.spu.dto.ProductSpuUpdateDTO;
import cn.boot.st.product.controller.spu.vo.ProductSpuRespVO;
import cn.boot.st.product.dataobject.bo.ProductSkuCreateOrUpdateBO;
import cn.boot.st.product.dataobject.domain.ProductSpu;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    List<ProductSkuCreateOrUpdateBO> convert2(List<ProductSpuUpdateDTO.Sku> sku);

    @Mapping(source = "picUrls", target = "picUrls", qualifiedByName = "translatePicUrlsFromStringList")
    ProductSpu convert(ProductSpuUpdateDTO productSkuCreateOrUpdateBO);

    @Named("translatePicUrlsFromStringList")
    default String translatePicUrlsFromList(List<String> picUrls) {
        return StringUtils.join(picUrls, ",");
    }


    @Mapping(source = "picUrls", target = "picUrls", qualifiedByName = "test")
    ProductSpuRespVO convert(ProductSpu productSpu);

    List<ProductSpuRespVO> convertList(List<ProductSpu> productSpuList);

    @Mapping(source = "records", target = "list")
    PageResult<ProductSpuRespVO> convertPage(IPage<ProductSpu> page);


    @Named("test")
    default List<String> translateAttrValueIdsToString(String urls) {
        String[] split = urls.split(",");
       return Arrays.stream(split).collect(Collectors.toList());
    }

}
