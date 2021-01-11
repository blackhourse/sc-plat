package cn.boot.st.tradeserver.service.order;

import cn.boot.common.framework.enums.CommonStatusEnum;
import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.productservice.bo.ProductAttrKeyValueBO;
import cn.boot.st.productservice.vo.sku.ProductSkuRespVo;
import cn.boot.st.productservice.vo.spu.ProductSpuRespVO;
import cn.boot.st.tradeserver.convert.OrderConvert;
import cn.boot.st.tradeserver.remote.ProductFeignService;
import cn.boot.st.tradeservice.service.order.TradeOrderService;
import cn.boot.st.tradeservice.service.order.dto.TradeOrderConfirmCreateInfoReqDto;
import cn.boot.st.tradeservice.service.order.vo.ConfirmCreateFee;
import cn.boot.st.tradeservice.service.order.vo.OrderConfirmCreateRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static cn.boot.st.tradeserver.constants.TradeErrorCodeConstants.CARD_ITEM_SKU_NOT_FOUND;
import static cn.boot.st.tradeserver.constants.TradeErrorCodeConstants.ORDER_PRODUCT_SKU_QUANTITY_NOT_ENOUGH;

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
    public OrderConfirmCreateRespVo getOrderConfirmCreateInfo(TradeOrderConfirmCreateInfoReqDto reqDto) {
        // 检查商品信息库存
        ProductSkuRespVo productSkuRespVo = this.checkProductSkuInfo(reqDto.getSkuId(), reqDto.getQuantity());
        // 计算商品价格
        ConfirmCreateFee fee = calculateProductPrice(reqDto.getSkuId(), reqDto.getQuantity(), 0);
        // 获取促销活动
        // 查询可用优惠卷
        // 常用收货地址
        // 封装信息返回
        ProductSpuRespVO spu = productSkuRespVo.getSpu();
        List<ProductAttrKeyValueBO> attrs = productSkuRespVo.getAttrs();
        OrderConfirmCreateRespVo.Spu convert = OrderConvert.INSTANCE.convert(spu);
        List<OrderConfirmCreateRespVo.AttrValueInfo> attrValueInfos = OrderConvert.INSTANCE.convert(attrs);
        OrderConfirmCreateRespVo.Sku sku = OrderConvert.INSTANCE.convert(productSkuRespVo);
        sku.setSpu(convert).setAttrValueIds(attrValueInfos);
        return new OrderConfirmCreateRespVo()
                .setSkuList(Arrays.asList(sku)).setFee(fee);

    }

    /**
     * 校验商品sku 库存等
     *
     * @param skuId
     * @param quantity
     */
    private ProductSkuRespVo checkProductSkuInfo(Integer skuId, Integer quantity) {
        CommonResult<ProductSkuRespVo> productSkuRespVo = productFeignService.getProductBySkuId(skuId);
        productSkuRespVo.checkError();
        ProductSkuRespVo productSku = productSkuRespVo.getData();
        // sku 状态
        if (productSku == null || !CommonStatusEnum.ENABLE.getValue().equals(productSku.getStatus())) {
            throw ServiceExceptionUtil.exception(CARD_ITEM_SKU_NOT_FOUND);
        }
        // 校验库存
        if (productSku.getQuantity() < quantity) {
            throw ServiceExceptionUtil.exception(ORDER_PRODUCT_SKU_QUANTITY_NOT_ENOUGH);
        }
        return productSku;
    }

    /**
     * 计算商品价格
     *
     * @param skuId
     * @param quantity
     * @param couponId
     */
    private ConfirmCreateFee calculateProductPrice(Integer skuId, Integer quantity, Integer couponId) {
        CommonResult<ProductSkuRespVo> productSkuRespVo = productFeignService.getProductBySkuId(skuId);
        productSkuRespVo.checkError();
        ProductSkuRespVo product = productSkuRespVo.getData();
        Integer price = product.getPrice() * quantity;
        // todo 优惠券减免
        return new ConfirmCreateFee().setPresentTotal(price);
    }
}
