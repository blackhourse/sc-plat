package cn.boot.st.productservice.service;


import cn.boot.st.productservice.bo.ProductSkuCreateOrUpdateBO;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-21
 **/
public interface ProductSkuService {

    void createProductSkus(Integer spuId, List<ProductSkuCreateOrUpdateBO> createSkuBos);

    void updateProductSkus(Integer spuId, List<ProductSkuCreateOrUpdateBO> createSkuBos);
}
