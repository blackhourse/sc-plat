package cn.boot.st.productservice.service;


import cn.boot.st.productservice.bo.ProductSkuCreateOrUpdateBO;
import cn.boot.st.productservice.dto.sku.ProductSkuListQueryReqDto;
import cn.boot.st.productservice.vo.sku.ProductSkuRespVo;

import java.util.List;
import java.util.Set;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-21
 **/
public interface ProductSkuService {

    /**
     * 获取sku信息
     * @param productSkuId
     * @return
     */
    ProductSkuRespVo getSkuInfo(Integer productSkuId);

    void createProductSkus(Integer spuId, List<ProductSkuCreateOrUpdateBO> createSkuBos);

    void updateProductSkus(Integer spuId, List<ProductSkuCreateOrUpdateBO> createSkuBos);

    /**
     * 获取sku info
     *
     * @param reqDto@return
     */
    List<ProductSkuRespVo>  skuInfoList(ProductSkuListQueryReqDto reqDto);

}
