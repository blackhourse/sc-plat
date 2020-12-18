package cn.boot.st.product.mapper;

import cn.boot.st.product.controller.brand.dto.ProductBrandPageReqDTO;
import cn.boot.st.product.dataobject.domain.ProductBrand;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * 商品品牌(product_brand)数据Mapper
 *
 * @author maht
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-12-11 17:45:53
 */
@Repository
public interface ProductBrandMapper extends BaseMapper<ProductBrand> {

    /**
     * 根据名称查询品牌
     *
     * @param brandName
     * @return
     */
    default ProductBrand selectByName(String brandName) {
        return selectOne(new LambdaQueryWrapper<ProductBrand>().eq(ProductBrand::getName, brandName));
    }


    default IPage<ProductBrand> selectPage(ProductBrandPageReqDTO dto) {
        return selectPage(new Page<ProductBrand>(dto.getPageNo(), dto.getPageSize()),
                new LambdaQueryWrapper<ProductBrand>()
                        .like(StringUtils.hasText(dto.getName()), ProductBrand::getName, dto.getName())
        );
    }


}
