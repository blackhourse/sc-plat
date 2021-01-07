package cn.boot.st.productservice.service;

import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.productservice.bo.ProductAttrKeyValueBO;
import cn.boot.st.productservice.dto.attr.*;
import cn.boot.st.productservice.vo.attr.ProductAttrKeyVO;
import cn.boot.st.productservice.vo.attr.ProductAttrValueRespVO;

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


    List<ProductAttrKeyValueBO> validProductAttr(Set<Integer> productAttrValueIds, boolean validStatus);
}
