package cn.boot.st.product.service.impl;

import cn.boot.st.product.dataobject.domain.ProductSpu;
import cn.boot.st.product.mapper.ProductSpuMapper;
import cn.boot.st.product.service.ProductSpuService;
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
    private ProductSpuMapper productSpuMapper;


    @Override
    public Integer createProductSpu(ProductSpu productSpu) {
        productSpuMapper.insert(productSpu);
        return productSpu.getId();
    }


}
