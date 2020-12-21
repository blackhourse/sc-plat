package cn.boot.st.product.service.impl;

import cn.boot.common.framework.enums.CommonStatusEnum;
import cn.boot.st.product.dataobject.bo.ProductSkuCreateOrUpdateBO;
import cn.boot.st.product.dataobject.domain.ProductSku;
import cn.boot.st.product.mapper.ProductSkuMapper;
import cn.boot.st.product.service.ProductSkuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-21
 **/
@Service
public class ProductSkuServiceImpl implements ProductSkuService {

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Override
    public void createProductSkus(Integer spuId, List<ProductSkuCreateOrUpdateBO> createSkuBos) {

        List<ProductSku> skus = createSkuBos.stream().map(skuBo -> new ProductSku()
                .setPrice(skuBo.getPrice()).setQuantity(skuBo.getQuantity())
                .setAttrs(StringUtils.join(skuBo.getAttrValueIds(), ","))
                .setStatus(CommonStatusEnum.ENABLE.getValue())
                .setSpuId(spuId)).collect(Collectors.toList());
        productSkuMapper.insertList(skus);
    }
}
