package cn.boot.st.tradeserver.service.order;

import cn.boot.common.framework.enums.CommonStatusEnum;
import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.productservice.vo.sku.ProductSkuRespVo;
import cn.boot.st.tradeserver.remote.ProductFeignService;
import cn.boot.st.tradeservice.service.order.TradeOrderService;
import cn.boot.st.tradeservice.service.order.dto.TradeOrderConfirmCreateInfoReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import static cn.boot.st.tradeserver.constants.TradeErrorCodeConstants.*;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-11
 **/
@Service
public class TradeOrderServiceImpl implements TradeOrderService {


    @Autowired
    private ProductFeignService productFeignService;

    @Override
    public void getOrderConfirmCreateInfo(TradeOrderConfirmCreateInfoReqDto reqDto) {
        // 检查商品信息库存
        this.checkProductSkuInfo(reqDto.getSkuId(), reqDto.getAttrValueId(), reqDto.getQuantity());
        // 计算商品价格
        // 获取促销活动
        // 查询可用优惠卷
        // 常用收货地址
        // 封装信息返回
    }

    /**
     * 校验商品sku 库存等
     *
     * @param skuId
     * @param attrValueId
     */
    private void checkProductSkuInfo(Integer skuId, Integer attrValueId, Integer quantity) {
        CommonResult<ProductSkuRespVo> productSkuRespVo = productFeignService.getProductBySkuId(skuId);
        productSkuRespVo.checkError();
        ProductSkuRespVo productSku = productSkuRespVo.getData();
        // sku 状态
        if (productSku == null || !CommonStatusEnum.ENABLE.getValue().equals(productSku.getStatus())) {
            throw ServiceExceptionUtil.exception(CARD_ITEM_SKU_NOT_FOUND);
        }
        // 属性规格
        HashSet<Integer> attrValueIdSet = new HashSet<>(productSku.getAttrValueIds());
        boolean contains = attrValueIdSet.contains(attrValueId);
        if (!contains) {
            throw ServiceExceptionUtil.exception(CARD_ITEM_SKU_ATTR_NOT_FOUND);
        }
        // 库存
        if (productSku.getQuantity() < quantity) {
            throw ServiceExceptionUtil.exception(ORDER_PRODUCT_SKU_QUANTITY_NOT_ENOUGH);
        }
    }
}
