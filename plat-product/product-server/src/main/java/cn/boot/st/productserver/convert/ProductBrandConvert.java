package cn.boot.st.productserver.convert;


import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.productserver.dataobject.ProductBrand;
import cn.boot.st.productservice.dto.brand.BrandUpdateStatusDTO;
import cn.boot.st.productservice.dto.brand.ProductBrandCreateDTO;
import cn.boot.st.productservice.dto.brand.ProductBrandPageReqDTO;
import cn.boot.st.productservice.dto.brand.ProductBrandUpdateDTO;
import cn.boot.st.productservice.vo.brand.ProductBrandRespVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-14
 **/
@Mapper
public interface ProductBrandConvert {

    ProductBrandConvert INSTANCE = Mappers.getMapper(ProductBrandConvert.class);

    ProductBrand convert(ProductBrandCreateDTO productBrandCreateDTO);

    ProductBrand convert(ProductBrandUpdateDTO productBrandCreateDTO);

    ProductBrandRespVO convert(ProductBrand productBrand);

    List<ProductBrandRespVO> convert(List<ProductBrand> productBrands);

    ProductBrand convert(ProductBrandPageReqDTO bean);

    ProductBrand convert(BrandUpdateStatusDTO brandUpdateStatusDTO);

    @Mapping(source = "records", target = "list")
    PageResult<ProductBrandRespVO> convertPage(IPage<ProductBrand> page);


}
