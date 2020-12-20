package cn.boot.st.product.mapper;

import cn.boot.common.framework.util.StringUtils;
import cn.boot.st.product.controller.attr.dto.ProductAttrKeyPageReqDTO;
import cn.boot.st.product.dataobject.domain.ProductAttr;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

/**
 * product_attr(product_attr)数据Mapper
 *
 * @author maht
 * @description
 * @since 2020-12-17 10:57:21
 */
@Repository
public interface ProductAttrMapper extends BaseMapper<ProductAttr> {

    default ProductAttr selectByName(String name) {
        return selectOne(new LambdaQueryWrapper<ProductAttr>().eq(ProductAttr::getName, name));
    }


    default IPage<ProductAttr> selectPage(ProductAttrKeyPageReqDTO productAttrKeyPageReqDTO) {
        return selectPage(new Page<ProductAttr>(productAttrKeyPageReqDTO.getPageNo(), productAttrKeyPageReqDTO.getPageSize()),
                new LambdaQueryWrapper<ProductAttr>()
                        .like(StringUtils.hasText(productAttrKeyPageReqDTO.getName()), ProductAttr::getName, productAttrKeyPageReqDTO.getName())
                        .eq(ProductAttr::getStatus, productAttrKeyPageReqDTO.getStatus())
        );
    }

}
