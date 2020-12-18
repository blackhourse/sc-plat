package cn.boot.st.product.service;

import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.product.controller.attr.dto.*;
import cn.boot.st.product.controller.attr.vo.ProductAttrKeyVO;
import cn.boot.st.product.controller.attr.vo.ProductAttrValueRespVO;
import cn.boot.st.product.dataobject.domain.ProductAttrValue;

import java.util.List;
import java.util.Set;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-17
 **/
public interface ProductAttrService {

    /**
     * 商品规格添加
     *
     * @param dto
     * @return
     */
    Integer createProductAttrKey(ProductAttrKeyCreateDTO dto);

    /**
     * 获取规格键信息
     *
     * @param attrKeyId
     * @return
     */
    ProductAttrKeyVO getProductAttrKeyInfo(Integer attrKeyId);

    /**
     * 更新规格键
     *
     * @param productAttrKeyUpdateDTO
     */
    void updateProductAttrKey(ProductAttrKeyUpdateDTO productAttrKeyUpdateDTO);

    /**
     * 商品规格键 分页
     *
     * @param productAttrKeyPageReqDTO
     * @return
     */
    PageResult<ProductAttrKeyVO> pageProductAttrKey(ProductAttrKeyPageReqDTO productAttrKeyPageReqDTO);

    /**
     * 添加属性value
     *
     * @param productAttrValueCreateDTO
     * @return
     */
    Integer createAttrValue(ProductAttrValueCreateDTO productAttrValueCreateDTO);

    /**
     * 商品规格value 更新
     *
     * @param productAttrValueUpdateDTO
     */
    void updateAttrValue(ProductAttrValueUpdateDTO productAttrValueUpdateDTO);

    /**
     * 获得商品规格值
     *
     * @param productAttrValueId
     * @return
     */
    ProductAttrValueRespVO getProductAttrValue(Integer productAttrValueId);

    /**
     * 获得商品规格值 -列表
     *
     * @param queryReqDTO
     * @return
     */
    List<ProductAttrValueRespVO> listProductAttrValues(ProductAttrValueListQueryReqDTO queryReqDTO);


    List<ProductAttrValue> validProductAttr(Set<Integer> productAttrValueIds, boolean validStatus);
}
