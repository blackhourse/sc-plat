package cn.boot.st.product.manager;

import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.st.product.controller.category.vo.ProductCategoryRespVO;
import cn.boot.st.product.controller.spu.dto.ProductSpuCreateDTO;
import cn.boot.st.product.convert.ProductSpuConvert;
import cn.boot.st.product.dataobject.bo.ProductSkuCreateOrUpdateBO;
import cn.boot.st.product.dataobject.domain.ProductAttrValue;
import cn.boot.st.product.enums.ProductCategoryIdEnum;
import cn.boot.st.product.service.ProductAttrService;
import cn.boot.st.product.service.ProductCategoryService;
import cn.boot.st.product.service.ProductSpuService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.boot.st.product.constant.ProductErrorCodeConstants.PRODUCT_SPU_CATEGORY_MUST_BE_LEVEL2;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-18
 **/
@Service
public class ProductSpuManager {

    @Autowired
    private ProductSpuService productSpuService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductAttrService productAttrService;

    private static ProductSpuManager self() {
        return (ProductSpuManager) AopContext.currentProxy();
    }


    public Integer createProductSpu(ProductSpuCreateDTO productSpuCreateDTO) {
        Integer productSpuId = self().createProductSpu1(productSpuCreateDTO);
        return productSpuId;
    }

    @Transactional
    public Integer createProductSpu1(ProductSpuCreateDTO productSpuCreateDTO) {
        // 校验商品分类是否合法
        ProductCategoryRespVO productCategoryRespVO = this.checkProductCategory(productSpuCreateDTO.getCid());

        List<ProductSkuCreateOrUpdateBO> productSkuCreateOrUpdateBOS = ProductSpuConvert.INSTANCE.convert(productSpuCreateDTO.getSkus());
        // todo
        return null;
    }

    private ProductCategoryRespVO checkProductCategory(Integer cid) {
        ProductCategoryRespVO productCategory = productCategoryService.getProductCategory(cid);
        if (ProductCategoryIdEnum.ROOT.getId().equals(productCategory.getPid())) {
            // 商品只能添加到二级分类下
            throw ServiceExceptionUtil.exception(PRODUCT_SPU_CATEGORY_MUST_BE_LEVEL2);
        }
        return productCategory;
    }

    private List<ProductSkuCreateOrUpdateBO> checkProductAttr(List<ProductSkuCreateOrUpdateBO> skuBOs) {

        // 第一步，校验 SKU 使用到的规格是否存在
        HashSet<Integer> attrValueIds = new HashSet<>();
        skuBOs.stream().forEach(skuCreateOrUpdateBO -> attrValueIds.addAll(skuCreateOrUpdateBO.getAttrValueIds()));
        List<ProductAttrValue> productAttrValues = productAttrService.validProductAttr(attrValueIds, true);
        // 第二步，校验 SKU 设置的规格是否合法，例如说数量是否一致，是否重复等等
        // 创建 ProductAttrDetailBO 的映射。其中，KEY 为 ProductAttrDetailBO.attrValueId ，即规格值的编号

        Map<Integer, ProductAttrValue> attrValueMap = productAttrValues.stream().collect(Collectors.toMap(ProductAttrValue::getAttrId, a -> a));
        // todo
        return null;

    }

}
