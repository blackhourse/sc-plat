package cn.boot.st.productserver.mapper;

import cn.boot.common.framework.util.StringUtils;
import cn.boot.st.productserver.dataobject.ProductSpu;
import cn.boot.st.productservice.dto.spu.ProductSpuPageReqDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

/**
 * product_spu(product_spu)数据Mapper
 *
 * @author maht
 * @description
 * @since 2020-12-17 10:57:21
 */
@Repository
public interface ProductSpuMapper extends BaseMapper<ProductSpu> {

    default IPage<ProductSpu> selectPage(ProductSpuPageReqDTO pageReqDTO) {
        LambdaQueryWrapper<ProductSpu> queryWrapper = new LambdaQueryWrapper<ProductSpu>()
                .like(StringUtils.hasText(pageReqDTO.getName()), ProductSpu::getName, pageReqDTO.getName())
                .eq(pageReqDTO.getCid() != null, ProductSpu::getCid, pageReqDTO.getCid())
                .eq(pageReqDTO.getVisible() != null, ProductSpu::getVisible, pageReqDTO.getVisible());

        // 库存过滤
        if (pageReqDTO.getHasQuantity() != null) {
            if (pageReqDTO.getHasQuantity()) {
                queryWrapper.gt(ProductSpu::getQuantity, 0);
            } else {
                queryWrapper.eq(ProductSpu::getQuantity, 0);
            }
        }
        return selectPage(new Page<>(pageReqDTO.getPageNo(), pageReqDTO.getPageSize()), queryWrapper);
    }

}
