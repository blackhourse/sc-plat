package cn.boot.st.product.convert;

import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.product.controller.brand.dto.BrandUpdateStatusDTO;
import cn.boot.st.product.controller.brand.dto.ProductBrandCreateDTO;
import cn.boot.st.product.controller.brand.dto.ProductBrandPageReqDTO;
import cn.boot.st.product.controller.brand.dto.ProductBrandUpdateDTO;
import cn.boot.st.product.controller.brand.vo.ProductBrandRespVO;
import cn.boot.st.product.dataobject.domain.ProductBrand;
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
