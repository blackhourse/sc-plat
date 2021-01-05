package cn.boot.st.product.service.impl;

import cn.boot.common.framework.enums.CommonStatusEnum;
import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.product.controller.attr.dto.*;
import cn.boot.st.product.controller.attr.vo.ProductAttrKeyVO;
import cn.boot.st.product.controller.attr.vo.ProductAttrValueRespVO;
import cn.boot.st.product.convert.ProductAttrConvert;
import cn.boot.st.product.dataobject.bo.ProductAttrKeyValueBO;
import cn.boot.st.product.dataobject.domain.ProductAttr;
import cn.boot.st.product.dataobject.domain.ProductAttrValue;
import cn.boot.st.product.mapper.ProductAttrMapper;
import cn.boot.st.product.mapper.ProductAttrValueMapper;
import cn.boot.st.product.service.ProductAttrService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.boot.st.product.constants.ProductErrorCodeConstants.*;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-17
 **/
@Service
public class ProductAttrServiceImpl implements ProductAttrService {

    @Autowired
    private ProductAttrMapper productAttrMapper;

    @Autowired
    private ProductAttrValueMapper productAttrValueMapper;

    @Override
    public Integer createProductAttrKey(ProductAttrKeyCreateDTO dto) {
        ProductAttr productAttr = productAttrMapper.selectByName(dto.getName());
        // 商品规格名称已存在
        if (productAttr != null) {
            throw ServiceExceptionUtil.exception(PRODUCT_ATTR_KEY_EXISTS);
        }
        ProductAttr convert = ProductAttrConvert.INSTANCE.convert(dto);
        productAttrMapper.insert(convert);
        return convert.getId();
    }

    @Override
    public ProductAttrKeyVO getProductAttrKeyInfo(Integer attrKeyId) {
        ProductAttr productAttr = productAttrMapper.selectById(attrKeyId);

        if (productAttr == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_ATTR_KEY_NOT_EXIST);
        }
        return ProductAttrConvert.INSTANCE.convert(productAttr);
    }

    @Override
    public void updateProductAttrKey(ProductAttrKeyUpdateDTO productAttrKeyUpdateDTO) {

        ProductAttr productAttr = productAttrMapper.selectById(productAttrKeyUpdateDTO.getId());
        // 规格键是否存在
        if (productAttr == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_ATTR_KEY_NOT_EXIST);
        }
        // 检查规格键是否存在
        ProductAttr attr = productAttrMapper.selectByName(productAttrKeyUpdateDTO.getName());
        if (attr != null && !attr.getId().equals(productAttrKeyUpdateDTO.getId())) {
            throw ServiceExceptionUtil.exception(PRODUCT_ATTR_KEY_EXISTS);
        }

        ProductAttr convert = ProductAttrConvert.INSTANCE.convert(productAttrKeyUpdateDTO);
        productAttrMapper.updateById(convert);

    }

    @Override
    public PageResult<ProductAttrKeyVO> pageProductAttrKey(ProductAttrKeyPageReqDTO productAttrKeyPageReqDTO) {
        IPage<ProductAttr> selectPage = productAttrMapper.selectPage(productAttrKeyPageReqDTO);
        return ProductAttrConvert.INSTANCE.convertPage(selectPage);
    }

    @Override
    public Integer createAttrValue(ProductAttrValueCreateDTO productAttrValueCreateDTO) {
        ProductAttr productAttr = productAttrMapper.selectById(productAttrValueCreateDTO.getAttrId());
        // 规格键是否存在
        if (productAttr == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_ATTR_KEY_NOT_EXIST);
        }
        // 检查规格value 的名称是否重复
        ProductAttrValue productAttrValue = productAttrValueMapper.selectByAttrIdAndValueName(productAttrValueCreateDTO.getAttrId(), productAttrValueCreateDTO.getName());
        if (productAttrValue != null) {
            throw ServiceExceptionUtil.exception(PRODUCT_ATTR_VALUE_EXISTS);
        }
        ProductAttrValue convert = ProductAttrConvert.INSTANCE.convert(productAttrValueCreateDTO);
        productAttrValueMapper.insert(convert);
        return convert.getId();

    }

    @Override
    public void updateAttrValue(ProductAttrValueUpdateDTO productAttrValueUpdateDTO) {

        ProductAttrValue productAttrValue1 = productAttrValueMapper.selectById(productAttrValueUpdateDTO.getId());
        // 规格value 不存在
        if (productAttrValue1 == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_ATTR_VALUE_NOT_EXIST);
        }

        ProductAttr productAttr = productAttrMapper.selectById(productAttrValueUpdateDTO.getAttrId());
        // 规格键是否存在
        if (productAttr == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_ATTR_KEY_EXISTS);
        }
        // 检查规格value 的名称是否重复
        ProductAttrValue productAttrValue = productAttrValueMapper.selectByAttrIdAndValueName(productAttrValueUpdateDTO.getAttrId(), productAttrValueUpdateDTO.getName());
        if (productAttrValue != null && !productAttrValue.getId().equals(productAttrValueUpdateDTO.getId())) {
            throw ServiceExceptionUtil.exception(PRODUCT_ATTR_VALUE_EXISTS);
        }
        // 更新到数据库
        ProductAttrValue attrValue = ProductAttrConvert.INSTANCE.convert(productAttrValueUpdateDTO);
        productAttrValueMapper.updateById(attrValue);
    }

    @Override
    public ProductAttrValueRespVO getProductAttrValue(Integer productAttrValueId) {
        ProductAttrValue productAttrValue = productAttrValueMapper.selectById(productAttrValueId);
        return ProductAttrConvert.INSTANCE.convert(productAttrValue);
    }

    @Override
    public List<ProductAttrValueRespVO> listProductAttrValues(ProductAttrValueListQueryReqDTO queryReqDTO) {
        List<ProductAttrValue> productAttrValues = productAttrValueMapper.selectList(queryReqDTO);
        return ProductAttrConvert.INSTANCE.convert(productAttrValues);
    }

    @Override
    public List<ProductAttrKeyValueBO> validProductAttr(Set<Integer> productAttrValueIds, boolean validStatus) {
        // 首先，校验规格 Value
        List<ProductAttrValue> attrValues = productAttrValueMapper.selectBatchIds(productAttrValueIds);
        if (attrValues.size() != productAttrValueIds.size()) {
            throw ServiceExceptionUtil.exception(PRODUCT_ATTR_VALUE_NOT_EXIST);
        }
        if (validStatus) {
            // 同时，校验下状态
            for (ProductAttrValue attrValue : attrValues) {
                if (CommonStatusEnum.DISABLE.getValue().equals(attrValue.getStatus())) {
                    throw ServiceExceptionUtil.exception(PRODUCT_ATTR_VALUE_EXISTS);
                }
            }
        }
        // 检查规格key 是否相等
        Set<Integer> attrKeyIds = attrValues.stream().map(ProductAttrValue::getAttrId).collect(Collectors.toSet());
        List<ProductAttr> productAttrs = productAttrMapper.selectBatchIds(attrKeyIds);
        if (attrKeyIds.size() != productAttrs.size()) {
            throw ServiceExceptionUtil.exception(PRODUCT_ATTR_KEY_NOT_EXIST);
        }
        if (validStatus) {
            for (ProductAttr attrKey : productAttrs) {
                if (CommonStatusEnum.DISABLE.getValue().equals(attrKey.getStatus())) {
                    throw ServiceExceptionUtil.exception(PRODUCT_ATTR_KEY_NOT_EXIST);
                }
            }
        }

        Map<Integer, ProductAttr> productAttrMap = productAttrs.stream().collect(Collectors.toMap(ProductAttr::getId, a -> a));
        return attrValues.stream().map(attrValue -> new ProductAttrKeyValueBO().setAttrKeyId(attrValue.getAttrId()).setAttrKeyName(productAttrMap.get(attrValue.getAttrId()).getName())
                .setAttrValueId(attrValue.getId()).setAttrValueName(attrValue.getName())).collect(Collectors.toList());
    }
}
