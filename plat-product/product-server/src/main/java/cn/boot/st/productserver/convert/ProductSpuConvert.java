package cn.boot.st.productserver.convert;

import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.productserver.dataobject.ProductSpu;
import cn.boot.st.productservice.bo.ProductSkuCreateOrUpdateBO;
import cn.boot.st.productservice.bo.ProductSpuBo;
import cn.boot.st.productservice.dto.spu.ProductSpuCreateDTO;
import cn.boot.st.productservice.dto.spu.ProductSpuUpdateDTO;
import cn.boot.st.productservice.vo.spu.ProductSpuRespVO;
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
    ProductSpuBo convert(ProductSpuCreateDTO bean);

    List<ProductSkuCreateOrUpdateBO> convert2(List<ProductSpuUpdateDTO.Sku> sku);

    @Mapping(source = "picUrls", target = "picUrls", qualifiedByName = "translatePicUrlsFromStringList")
    ProductSpuBo convert(ProductSpuUpdateDTO productSkuCreateOrUpdateBO);

    @Named("translatePicUrlsFromStringList")
    default String translatePicUrlsFromList(List<String> picUrls) {
        return StringUtils.join(picUrls, ",");
    }


    @Mapping(source = "picUrls", target = "picUrls", qualifiedByName = "test")
    ProductSpuRespVO convert(ProductSpu productSpu);

    List<ProductSpuRespVO> convertList(List<ProductSpu> productSpuList);

    @Mapping(source = "records", target = "list")
    PageResult<ProductSpuRespVO> convertPage(IPage<ProductSpu> page);


    ProductSpu convert(ProductSpuBo productSpuBo);


    @Named("test")
    default List<String> translateAttrValueIdsToString(String urls) {
        String[] split = urls.split(",");
        return Arrays.stream(split).collect(Collectors.toList());
    }

}
