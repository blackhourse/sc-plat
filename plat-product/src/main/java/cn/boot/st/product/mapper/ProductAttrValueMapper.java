package cn.boot.st.product.mapper;

import cn.boot.st.product.controller.attr.dto.ProductAttrValueListQueryReqDTO;
import cn.boot.st.product.dataobject.domain.ProductAttrValue;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * product_attr(product_attr_value)数据Mapper
 *
 * @author maht
 * @description
 * @since 2020-12-17 10:57:21
 */
@Repository
public interface ProductAttrValueMapper extends BaseMapper<ProductAttrValue> {

    default ProductAttrValue selectByAttrIdAndValueName(Integer attrId, String attrValueName) {
        return selectOne(
                new LambdaQueryWrapper<ProductAttrValue>()
                        .eq(ProductAttrValue::getAttrId, attrId)
                        .eq(ProductAttrValue::getName, attrValueName)
        );
    }

    default List<ProductAttrValue> selectList(ProductAttrValueListQueryReqDTO queryReqDTO) {
        return selectList(new LambdaQueryWrapper<ProductAttrValue>()
                .in(CollectionUtil.isNotEmpty(queryReqDTO.getProductAttrValueIds()), ProductAttrValue::getId, queryReqDTO.getProductAttrValueIds())
                .eq(ProductAttrValue::getAttrId, queryReqDTO.getProductAttrKeyId())
        );

    }


}
