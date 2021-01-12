package cn.boot.st.tradeserver.service.order;

import cn.boot.common.framework.enums.CommonStatusEnum;
import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.common.framework.util.SeqGenerateUtil;
import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.productservice.bo.ProductAttrKeyValueBO;
import cn.boot.st.productservice.dto.sku.ProductSkuListQueryReqDto;
import cn.boot.st.productservice.enums.ProductSkuDetailFieldEnum;
import cn.boot.st.productservice.vo.sku.ProductSkuRespVo;
import cn.boot.st.productservice.vo.spu.ProductSpuRespVO;
import cn.boot.st.tradeserver.convert.OrderConvert;
import cn.boot.st.tradeserver.dataobject.CartItem;
import cn.boot.st.tradeserver.dataobject.OrderItem;
import cn.boot.st.tradeserver.dataobject.TradeOrder;
import cn.boot.st.tradeserver.enums.LogisticsDeliveryTypeEnum;
import cn.boot.st.tradeserver.enums.TradeOrderAfterSaleStatusEnum;
import cn.boot.st.tradeserver.enums.TradeOrderStatusEnum;
import cn.boot.st.tradeserver.mapper.CartItemMapper;
import cn.boot.st.tradeserver.mapper.OrderItemMapper;
import cn.boot.st.tradeserver.mapper.TradeOrderMapper;
import cn.boot.st.tradeserver.remote.ProductFeignService;
import cn.boot.st.tradeservice.service.dto.TradeOrderConfirmCreateInfoReqDto;
import cn.boot.st.tradeservice.service.dto.TradeOrderCreateReqDto;
import cn.boot.st.tradeservice.service.order.TradeOrderService;
import cn.boot.st.tradeservice.service.vo.OrderConfirmCreateRespVo;
import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static cn.boot.st.tradeserver.constants.TradeErrorCodeConstants.*;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-11
 **/
@Service
public class TradeOrderServiceImpl implements TradeOrderService {


    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private ProductFeignService productFeignService;

    @Autowired
    private TradeOrderMapper tradeOrderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

 /*   private static TradeOrderServiceImpl self() {
        return (TradeOrderServiceImpl) AopContext.currentProxy();
    }
*/

    @Override
    public OrderConfirmCreateRespVo getOrderConfirmCreateInfo(TradeOrderConfirmCreateInfoReqDto reqDto) {
        // 检查商品信息库存
        Map<Integer, Integer> map = new HashMap<>();
        map.put(reqDto.getSkuId(), reqDto.getQuantity());
        this.checkProductSkuInfo(map);
        // 计算商品价格
        // 获取促销活动
        // 查询可用优惠卷
        // 常用收货地址
        // 封装信息返回
        return getOrderConfirmCreateInfo0(map);
    }

    public OrderConfirmCreateRespVo getOrderConfirmCreateInfo0(Map<Integer, Integer> skuMap) {
        Map<Integer, ProductSkuRespVo> productSkuRespVo = this.checkProductSkuInfo(skuMap);
        OrderConfirmCreateRespVo.Fee fee = calculateProductPrice(productSkuRespVo, skuMap, 0);
        Iterator<Map.Entry<Integer, ProductSkuRespVo>> iterator = productSkuRespVo.entrySet().iterator();
        List<OrderConfirmCreateRespVo.Sku> skuList = new ArrayList<>(skuMap.size());

        while (iterator.hasNext()) {
            ProductSkuRespVo skuRespVo = iterator.next().getValue();
            ProductSpuRespVO spu = skuRespVo.getSpu();
            List<ProductAttrKeyValueBO> attrs = skuRespVo.getAttrs();
            OrderConfirmCreateRespVo.Spu convert = OrderConvert.INSTANCE.convert(spu);
            List<OrderConfirmCreateRespVo.AttrValueInfo> attrValueInfos = OrderConvert.INSTANCE.convert(attrs);
            OrderConfirmCreateRespVo.Sku sku = OrderConvert.INSTANCE.convert(skuRespVo);
            sku.setSpu(convert).setAttrValueIds(attrValueInfos).setBuyQuantity(skuMap.get(sku.getId()));
            skuList.add(sku);
        }
        return new OrderConfirmCreateRespVo()
                .setSkuList(skuList).setFee(fee);

    }


    @Override
    public OrderConfirmCreateRespVo getOrderConfirmCreateInfoFromCart(Integer userId, Integer couponCardId) {
        List<CartItem> cartItems = cartItemMapper.selectList(userId, true);
        if (CollUtil.isEmpty(cartItems)) {
            return new OrderConfirmCreateRespVo().setSkuList(Collections.emptyList())
                    .setFee(new OrderConfirmCreateRespVo.Fee().setPresentTotal(0).setBuyTotal(0).setDiscountTotal(0).setPostageTotal(0));
        }
        Map<Integer, Integer> itemMap = cartItems.stream().collect(Collectors.toMap(CartItem::getSkuId, CartItem::getQuantity));
        return getOrderConfirmCreateInfo0(itemMap);
    }

    @Override
    public Integer createTradeOrder(TradeOrderCreateReqDto createReqDto) {
        // 收获地址
        // 获取商品信息
        // 获得商品信息
        List<TradeOrderCreateReqDto.OrderItem> orderItems = createReqDto.getOrderItems();
        CommonResult<List<ProductSkuRespVo>> skuInfoList = productFeignService.getSkuInfoList(
                new ProductSkuListQueryReqDto().setProductSkuIds(orderItems.stream().map(TradeOrderCreateReqDto.OrderItem::getSkuId).collect(Collectors.toSet()))
                        .setFields(Arrays.asList(ProductSkuDetailFieldEnum.ATTR.getField(), ProductSkuDetailFieldEnum.SPU.getField()))
        );
        skuInfoList.checkError();
        List<ProductSkuRespVo> skuInfoListData = skuInfoList.getData();
        // 校验获得的数量，是否匹配
        if (skuInfoListData.size() != createReqDto.getOrderItems().size()) {
            throw ServiceExceptionUtil.exception(ORDER_GET_GOODS_INFO_INCORRECT);
        }
        // 计算价格
        Map<Integer, ProductSkuRespVo> skuRespVoMap = skuInfoListData.stream().collect(Collectors.toMap(ProductSkuRespVo::getId, sku -> sku));
        Map<Integer, Integer> skuQuantityMap = orderItems.stream().collect(Collectors.toMap(TradeOrderCreateReqDto.OrderItem::getSkuId, TradeOrderCreateReqDto.OrderItem::getQuantity));
        OrderConfirmCreateRespVo.Fee fee = calculateProductPrice(skuRespVoMap, skuQuantityMap, 0);
        // 标记优惠券
        // 创建订单
        return this.createTradeOrder(createReqDto, skuInfoListData, fee).getId();
    }

    private TradeOrder createTradeOrder(TradeOrderCreateReqDto createReqDto, List<ProductSkuRespVo> skuInfoListData, OrderConfirmCreateRespVo.Fee fee) {


        TradeOrder tradeOrder = new TradeOrder();
        tradeOrder.setUserId(createReqDto.getUserId()).setOrderNo(SeqGenerateUtil.getId())
                .setOrderStatus(TradeOrderStatusEnum.WAITING_PAYMENT.getValue()).setRemark(createReqDto.getRemark());
        // 价格
        tradeOrder.setBuyPrice(fee.getPresentTotal()).setPresentPrice(fee.getPresentTotal());
        // 3. 收件 + 物流基本信息
        tradeOrder.setDeliveryType(LogisticsDeliveryTypeEnum.EXPRESS.getDeliveryType());

        // 4. 售后基本信息
        tradeOrder.setAfterSaleStatus(TradeOrderAfterSaleStatusEnum.NULL.getStatus());
        // 5. 营销基本信息
        tradeOrder.setCouponCardId(0);
        // 最终保存
        tradeOrderMapper.insert(tradeOrder);
        Map<List<Integer>, ProductSkuRespVo> skuRespVoMap = skuInfoListData.stream().collect(Collectors.toMap(ProductSkuRespVo::getAttrValueIds, sku -> sku));

        ArrayList<OrderItem> orderItems = new ArrayList<>(createReqDto.getOrderItems().size());

        createReqDto.getOrderItems().forEach(orderItem -> {
            ProductSkuRespVo productSkuRespVo = skuRespVoMap.get(orderItem.getSkuId());
            OrderItem item = new OrderItem()
                    .setOrderId(tradeOrder.getId())
                    .setOrderNo(tradeOrder.getOrderNo())
                    .setSkuId(productSkuRespVo.getId())
                    .setSkuName(productSkuRespVo.getSpu().getName())
                    .setSkuImage(productSkuRespVo.getPicUrl())
                    .setQuantity(orderItem.getQuantity())
                    .setBuyPrice(productSkuRespVo.getPrice())
                    .setOriginPrice(productSkuRespVo.getPrice())
                    .setPresentPrice(productSkuRespVo.getPrice())
                    .setBuyTotal(productSkuRespVo.getPrice() * orderItem.getQuantity());
            orderItems.add(item);
        });
        orderItemMapper.batchInsert(orderItems);
        return tradeOrder;
    }


    /**
     * 校验商品sku 库存等
     *
     * @param skuMap
     */
    private Map<Integer, ProductSkuRespVo> checkProductSkuInfo(Map<Integer, Integer> skuMap) {
        ProductSkuListQueryReqDto skuQueryReqDto = new ProductSkuListQueryReqDto().setProductSkuIds(skuMap.keySet())
                .setFields(Arrays.asList(ProductSkuDetailFieldEnum.ATTR.getField(), ProductSkuDetailFieldEnum.SPU.getField()));
        CommonResult<List<ProductSkuRespVo>> skuInfoList = productFeignService.getSkuInfoList(skuQueryReqDto);
        skuInfoList.checkError();
        List<ProductSkuRespVo> productSkuRespVos = skuInfoList.getData();
        Map<Integer, ProductSkuRespVo> skuRespVo = productSkuRespVos.stream().collect(Collectors.toMap(ProductSkuRespVo::getId, sku -> sku));
        for (Map.Entry<Integer, Integer> entry : skuMap.entrySet()) {
            ProductSkuRespVo productSku = skuRespVo.get(entry.getKey());
            // sku 状态
            if (productSku == null || !CommonStatusEnum.ENABLE.getValue().equals(productSku.getStatus())) {
                throw ServiceExceptionUtil.exception(CARD_ITEM_SKU_NOT_FOUND);
            }
            // 校验库存
            if (productSku.getQuantity() < productSku.getQuantity()) {
                throw ServiceExceptionUtil.exception(ORDER_PRODUCT_SKU_QUANTITY_NOT_ENOUGH);
            }
        }
        return skuRespVo;
    }

    /**
     * 计算商品价格
     *
     * @param skuMap
     * @param couponId
     */
    private OrderConfirmCreateRespVo.Fee calculateProductPrice(Map<Integer, ProductSkuRespVo> skuMap, Map<Integer, Integer> map, Integer couponId) {
        Integer price = 0;
        Iterator<Map.Entry<Integer, ProductSkuRespVo>> iterator = skuMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, ProductSkuRespVo> next = iterator.next();
            ProductSkuRespVo productSkuRespVo = next.getValue();
            Integer skuId = next.getKey();
            price += productSkuRespVo.getPrice() * map.get(skuId);
        }
        // todo 优惠券减免
        return new OrderConfirmCreateRespVo.Fee().setPresentTotal(price);
    }
}
