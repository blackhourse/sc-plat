package cn.boot.st.product.service;

import cn.boot.st.product.dataobject.domain.ProductSpu;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-18
 **/
public interface ProductSpuService {

    /**
     * 创建商品-添加spu
     *
     * @param productSpu
     * @return
     */
    Integer createProductSpu(ProductSpu productSpu);

    /**
     * 修改商品sku
     * @param productSpu
     */
    void updateProductSpu(ProductSpu productSpu);

}
