package cn.boot.st.product.service;

import cn.boot.st.product.controller.spu.dto.ProductSpuCreateDTO;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-18
 **/
public interface ProductSpuService {

    /**
     * 创建上皮内-添加spu
     * @param productSpuCreateDTO
     * @return
     */
    Integer createProductSpu(ProductSpuCreateDTO productSpuCreateDTO);

}
