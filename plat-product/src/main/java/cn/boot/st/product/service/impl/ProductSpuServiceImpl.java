package cn.boot.st.product.service.impl;

import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.st.product.dataobject.domain.ProductSpu;
import cn.boot.st.product.mapper.ProductSpuMapper;
import cn.boot.st.product.service.ProductSpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static cn.boot.st.product.constant.ProductErrorCodeConstants.PRODUCT_SPU_NOT_EXISTS;

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

    @Override
    public void updateProductSpu(ProductSpu productSpu) {
        // 校验spu是否存在
        if (productSpuMapper.selectById(productSpu.getId()) == null){
            throw ServiceExceptionUtil.exception(PRODUCT_SPU_NOT_EXISTS);
        }
        productSpuMapper.updateById(productSpu);
    }


}
