package cn.boot.st.product.mapper;

import cn.boot.st.product.dataobject.domain.ProductSku;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * product_sku(product_sku)数据Mapper
 *
 * @author maht
 * @description
 * @since 2020-12-17 10:57:21
 */
@Repository
public interface ProductSkuMapper extends BaseMapper<ProductSku> {

    void insertList(@Param("productSkuList") List<ProductSku> productSkuList);


    default List<ProductSku> selectListBySpuIdAndStatus(Integer spuId, Integer state) {
        return selectList(new LambdaQueryWrapper<ProductSku>()
                .eq(ProductSku::getSpuId, spuId)
                .eq(ProductSku::getStatus, state));
    }

}
