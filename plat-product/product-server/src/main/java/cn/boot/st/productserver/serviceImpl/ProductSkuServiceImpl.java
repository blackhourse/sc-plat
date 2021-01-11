package cn.boot.st.productserver.serviceImpl;

import cn.boot.common.framework.enums.CommonStatusEnum;
import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.st.productserver.convert.ProductSkuConvert;
import cn.boot.st.productserver.dataobject.ProductSku;
import cn.boot.st.productserver.mapper.ProductSkuMapper;
import cn.boot.st.productserver.mapper.ProductSpuMapper;
import cn.boot.st.productservice.bo.ProductAttrKeyValueBO;
import cn.boot.st.productservice.bo.ProductSkuCreateOrUpdateBO;
import cn.boot.st.productservice.dto.sku.ProductSkuListQueryReqDto;
import cn.boot.st.productservice.enums.ProductSkuDetailFieldEnum;
import cn.boot.st.productservice.service.ProductAttrService;
import cn.boot.st.productservice.service.ProductSkuService;
import cn.boot.st.productservice.service.ProductSpuService;
import cn.boot.st.productservice.vo.sku.ProductSkuRespVo;
import cn.boot.st.productservice.vo.spu.ProductSpuRespVO;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static cn.boot.st.productserver.constants.ProductErrorCodeConstants.PRODUCT_SPU_NOT_EXISTS;
import static cn.boot.st.productserver.constants.ProductErrorCodeConstants.PRODUCT_SkU_NOT_EXISTS;


/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-21
 **/
@Service
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSku> implements ProductSkuService {

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductSpuMapper productSpuMapper;

    @Autowired
    private ProductSpuService productSpuService;


    @Autowired
    private ProductAttrService productAttrService;


    @Override
    public ProductSkuRespVo getSkuInfo(Integer productSkuId) {
        ProductSku productSku = productSkuMapper.selectById(productSkuId);
        if (productSkuId == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_SkU_NOT_EXISTS);
        }
        List<ProductSkuRespVo> skuRespVos = this.sku(Arrays.asList(productSku), Arrays.asList(ProductSkuDetailFieldEnum.SPU.getField(), ProductSkuDetailFieldEnum.ATTR.getField()));
        return skuRespVos.get(0);
    }

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

    @Override
    public List<ProductSkuRespVo> skuInfoList(ProductSkuListQueryReqDto reqDto) {
        // sku
        List<ProductSku> productSkuList = productSkuMapper.selectBatchIds(reqDto.getProductSkuIds());
        if (CollUtil.isEmpty(productSkuList)) {
            return Lists.newArrayList();
        }
        return sku(productSkuList, reqDto.getFields());
    }

    private List<ProductSkuRespVo> sku(List<ProductSku> skuList, Collection<String> fields) {
        // 获得商品 SPU 列表
        List<ProductSpuRespVO> spuBOs = Collections.emptyList();
        if (fields.contains(ProductSkuDetailFieldEnum.SPU.getField())) {
            spuBOs = productSpuService.listProductSpus(
                    skuList.stream().map(ProductSku::getSpuId).collect(Collectors.toList()));
        }
        // 获取商品 SKU 的规格数组
        List<ProductAttrKeyValueBO> attrBOs = Collections.emptyList();
        if (fields.contains(ProductSkuDetailFieldEnum.ATTR.getField())) {
            Set<Integer> attrValueIds = new HashSet<>();
            skuList.forEach(sku -> {
                attrValueIds.addAll(Arrays.stream(sku.getAttrs().split(",")).map(s -> Integer.valueOf(s)).collect(Collectors.toList()));
            });
            // 读取规格时，不考虑规格是否被禁用
            attrBOs = productAttrService.validProductAttr(attrValueIds, false);
        }
        return this.convertList(skuList, spuBOs, attrBOs);
    }

    private List<ProductSkuRespVo> convertList(List<ProductSku> productSkuList, List<ProductSpuRespVO> spuBOs, List<ProductAttrKeyValueBO> attrKeyValueBOS) {
        // sku <spuId, spu>
        Map<Integer, ProductSpuRespVO> spuMap = spuBOs.stream().collect(Collectors.toMap(ProductSpuRespVO::getId, spu -> spu));
        // attr <attrValueId, attr>
        Map<Integer, ProductAttrKeyValueBO> attrMap = attrKeyValueBOS.stream().collect(Collectors.toMap(ProductAttrKeyValueBO::getAttrValueId, attr -> attr));

        ArrayList<ProductSkuRespVo> productSkuRespVos = new ArrayList<>(productSkuList.size());
        productSkuList.forEach(s -> {
            ProductSkuRespVo sku = ProductSkuConvert.INSTANCE.convert(s);
            sku.setSpu(spuMap.get(sku.getSpuId()));
            List<Integer> attrValueIds = sku.getAttrValueIds();
            sku.setAttrs(new ArrayList<>());
            attrValueIds.forEach(attr -> sku.getAttrs().add(attrMap.get(attr)));
            productSkuRespVos.add(sku);
        });
        return productSkuRespVos;
    }

}
