package cn.boot.st.productserver.convert;

import cn.boot.common.framework.vo.PageResult;

import cn.boot.st.productserver.dataobject.ProductCategory;
import cn.boot.st.productservice.dto.category.ProductCategoryCreateDTO;
import cn.boot.st.productservice.dto.category.ProductCategoryUpdateDTO;
import cn.boot.st.productservice.vo.category.ProductCategoryRespVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-17
 **/
@Mapper
public interface ProductCategoryConvert {
    ProductCategoryConvert INSTANCE = Mappers.getMapper(ProductCategoryConvert.class);

    ProductCategory convert(ProductCategoryCreateDTO dto);

    ProductCategory convert(ProductCategoryUpdateDTO dto);

    ProductCategoryRespVO convert(ProductCategory dto);

    List<ProductCategoryRespVO> convert(List<ProductCategory> list);

    @Mapping(source = "records", target = "list")
    PageResult<ProductCategoryRespVO> convertPage(IPage<ProductCategory> page);

}
