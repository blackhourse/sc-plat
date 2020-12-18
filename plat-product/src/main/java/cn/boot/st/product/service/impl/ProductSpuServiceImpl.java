package cn.boot.st.product.service.impl;

import cn.boot.st.product.controller.spu.dto.ProductSpuCreateDTO;
import cn.boot.st.product.manager.ProductSpuManager;
import cn.boot.st.product.service.ProductSpuService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-18
 **/

@Service
public class ProductSpuServiceImpl implements ProductSpuService {

    @Autowired
    private ProductSpuManager productSpuManager;



    @Override
    public Integer createProductSpu(ProductSpuCreateDTO productSpuCreateDTO) {
        return productSpuManager.createProductSpu(productSpuCreateDTO);
    }


}
