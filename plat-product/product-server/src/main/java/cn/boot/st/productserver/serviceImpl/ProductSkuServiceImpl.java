package cn.boot.st.productserver.serviceImpl;

import cn.boot.common.framework.enums.CommonStatusEnum;
import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.st.productserver.dataobject.ProductSku;
import cn.boot.st.productserver.mapper.ProductSkuMapper;
import cn.boot.st.productservice.bo.ProductSkuCreateOrUpdateBO;
import cn.boot.st.productservice.service.ProductSkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.boot.st.productserver.constants.ProductErrorCodeConstants.PRODUCT_SPU_NOT_EXISTS;


/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-21
 **/
@Service
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSku> implements ProductSkuService {

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

    @Override
    public void updateProductSkus(Integer spuId, List<ProductSkuCreateOrUpdateBO> createSkuBos) {
        // 校验sku是否存在
        // 检查s'ku id 不为空sku是否存在
        List<ProductSkuCreateOrUpdateBO> updateBOS = createSkuBos.stream().filter(productSkuCreateOrUpdateBO -> productSkuCreateOrUpdateBO.getId() != null).collect(Collectors.toList());
        Set<Integer> skuIds = updateBOS.stream().map(ProductSkuCreateOrUpdateBO::getId).collect(Collectors.toSet());
        List<ProductSku> productSkus1 = productSkuMapper.selectBatchIds(skuIds);
        if (productSkus1.size() != skuIds.size()) {
            throw ServiceExceptionUtil.exception(PRODUCT_SPU_NOT_EXISTS);
        }
        // 1.先删除 本次未传值的sku
        List<ProductSku> productSkus = productSkuMapper.selectListBySpuIdAndStatus(spuId, CommonStatusEnum.ENABLE.getValue());
        Set<Integer> allSkuIds = productSkus.stream().map(ProductSku::getId).collect(Collectors.toSet());
        Set<Integer> tempSet = allSkuIds;
        tempSet.removeAll(skuIds);
        productSkuMapper.deleteBatchIds(allSkuIds);
        // 2.更新已存在的sku
        List<ProductSku> existSkuList = updateBOS.stream().map(skuBo -> new ProductSku()
                .setId(skuBo.getId())
                .setPrice(skuBo.getPrice()).setQuantity(skuBo.getQuantity())
                .setAttrs(StringUtils.join(skuBo.getAttrValueIds(), ","))
                .setStatus(CommonStatusEnum.ENABLE.getValue())
                .setSpuId(spuId)).collect(Collectors.toList());
        this.saveOrUpdateBatch(existSkuList);


        // 3.保存新的
        List<ProductSkuCreateOrUpdateBO> tempList = createSkuBos;
        tempList.removeAll(updateBOS);
        List<ProductSku> skus = createSkuBos.stream().map(skuBo -> new ProductSku()
                .setPrice(skuBo.getPrice()).setQuantity(skuBo.getQuantity())
                .setAttrs(StringUtils.join(skuBo.getAttrValueIds(), ","))
                .setStatus(CommonStatusEnum.ENABLE.getValue())
                .setSpuId(spuId)).collect(Collectors.toList());
        productSkuMapper.insertList(skus);

    }
}
