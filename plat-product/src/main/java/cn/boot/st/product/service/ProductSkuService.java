package cn.boot.st.product.service;

import cn.boot.st.product.dataobject.bo.ProductSkuCreateOrUpdateBO;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-21
 **/
public interface ProductSkuService {

    void createProductSkus(Integer spuId, List<ProductSkuCreateOrUpdateBO> createSkuBos);
}
