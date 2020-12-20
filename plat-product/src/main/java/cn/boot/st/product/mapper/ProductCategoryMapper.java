package cn.boot.st.product.mapper;

import cn.boot.common.framework.util.StringUtils;
import cn.boot.st.product.controller.category.dto.ProductCategoryPageReqDTO;
import cn.boot.st.product.dataobject.domain.ProductCategory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

/**
 * 商品分类(product_category)数据Mapper
 *
 * @author maht
 * @description
 * @since 2020-12-17 10:51:55
 */
@Repository
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {


    /**
     * 获取父分类下的子分类数量
     *
     * @param pid
     * @return
     */
    default Integer selectCountChildByPid(Integer pid) {
        return selectCount(new LambdaQueryWrapper<ProductCategory>().eq(ProductCategory::getPid, pid));

    }

    default IPage<ProductCategory> selectPage(ProductCategoryPageReqDTO productCategoryPageReqDTO) {

        return selectPage(new Page<>(productCategoryPageReqDTO.getPageNo(), productCategoryPageReqDTO.getPageSize()),
                new LambdaQueryWrapper<ProductCategory>()
                        .like(StringUtils.hasText(productCategoryPageReqDTO.getName()), ProductCategory::getName,
                                productCategoryPageReqDTO.getName()));
    }


}
