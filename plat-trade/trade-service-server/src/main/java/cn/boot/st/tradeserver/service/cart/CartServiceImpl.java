package cn.boot.st.tradeserver.service.cart;

import cn.boot.common.framework.enums.CommonStatusEnum;
import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.productservice.dto.sku.ProductSkuListQueryReqDto;
import cn.boot.st.productservice.enums.ProductSkuDetailFieldEnum;
import cn.boot.st.productservice.vo.sku.ProductSkuRespVo;
import cn.boot.st.tradeserver.convert.CartConvert;
import cn.boot.st.tradeserver.dataobject.CartItem;
import cn.boot.st.tradeserver.enums.CartItemStatusEnum;
import cn.boot.st.tradeserver.mapper.CartItemMapper;
import cn.boot.st.tradeserver.remote.ProductFeignService;
import cn.boot.st.tradeservice.service.cart.CartService;
import cn.boot.st.tradeservice.service.cart.bo.CartProductItemBo;
import cn.boot.st.tradeservice.service.cart.dto.CartAddDto;
import cn.boot.st.tradeservice.service.cart.dto.CartQueryDto;
import cn.boot.st.tradeservice.service.cart.dto.CartUpdateDto;
import cn.boot.st.tradeservice.service.cart.dto.CartUpdateSelectDto;
import cn.boot.st.tradeservice.service.cart.vo.CartInfoVo;
import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static cn.boot.st.tradeserver.constants.TradeErrorCodeConstants.*;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-07
 **/
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private ProductFeignService productFeignService;


    @Override
    public Integer addCart(CartAddDto cartAddDto) {
        //1. // 校验商品 SKU 是否合法
        ProductSkuRespVo productSkuRespVo = this.checkProductSku(cartAddDto.getSkuId());
        Integer spuId = productSkuRespVo.getSpuId();
        CartItem cartItem = cartItemMapper.selectOneBySkuIdAndUserId(cartAddDto.getUserId(), cartAddDto.getSkuId());
        if (cartItem != null) {
            boolean isMoreQuantity = cartItem.getQuantity() + cartAddDto.getQuantity() > productSkuRespVo.getQuantity();
            if (isMoreQuantity) {
                // 校验库存
                throw ServiceExceptionUtil.exception(CARD_ITEM_SKU_QUANTITY_NOT_ENOUGH);
            }
            cartItem.setQuantity(cartItem.getQuantity() + cartAddDto.getQuantity());
            cartItemMapper.updateById(cartItem);
            return cartItem.getId();
        }
        CartItem item = CartConvert.INSTANCE.convert(cartAddDto);
        item.setSpuId(spuId);
        item.setSelected(Boolean.TRUE);
        item.setStatus(CartItemStatusEnum.NORMAL.getValue());
        cartItemMapper.insert(item);
        return item.getId();
    }

    @Override
    public void updateCartItemQuantity(CartUpdateDto cartUpdateDto) {
        ProductSkuRespVo productSkuRespVo = checkProductSku(cartUpdateDto.getSkuId());
        if (cartUpdateDto.getQuantity() > productSkuRespVo.getQuantity()) {
            // 校验库存
            throw ServiceExceptionUtil.exception(CARD_ITEM_SKU_QUANTITY_NOT_ENOUGH);
        }
        CartItem cartItem = cartItemMapper.selectOneBySkuIdAndUserId(cartUpdateDto.getUserId(), cartUpdateDto.getSkuId());
        cartItem.setQuantity(cartUpdateDto.getQuantity());
        cartItemMapper.updateById(cartItem);
    }

    @Override
    public Integer sumCartItemQuantity(Integer userId) {
        return cartItemMapper.selectSumProductByUserId(userId);
    }

    @Override
    public CartInfoVo getCartDetail(CartQueryDto cartQueryDto) {
        // 购物车列表
        List<CartItem> cartItems = cartItemMapper.selectList(cartQueryDto.getUserId(), cartQueryDto.getSelected());
        // 为空的话直接返回
        if (CollUtil.isEmpty(cartItems)) {
            return null;
        }
        // 获取sku 信息
        List<CartProductItemBo> productItemBos = CartConvert.INSTANCE.convert(cartItems);
        Set<Integer> skuIds = productItemBos.stream().map(CartProductItemBo::getSkuId).collect(Collectors.toSet());
        CommonResult<List<ProductSkuRespVo>> skuInfoList = productFeignService.getSkuInfoList(
                new ProductSkuListQueryReqDto().setProductSkuIds(skuIds)
                        .setFields(Arrays.asList(ProductSkuDetailFieldEnum.ATTR.getField(), ProductSkuDetailFieldEnum.SPU.getField()))
        );
        skuInfoList.checkError();
        List<ProductSkuRespVo> skuInfoListData = skuInfoList.getData();
        if (CollUtil.isEmpty(skuInfoListData)) {
            throw ServiceExceptionUtil.exception(CARD_ITEM_SKU_NOT_FOUND);
        }
        Map<Integer, ProductSkuRespVo> skuMap = skuInfoListData.stream().collect(Collectors.toMap(ProductSkuRespVo::getId, sku -> sku));
        // 获取spu信息
        // 计算选中的商品总价格
        AtomicReference<Integer> totalPrice = new AtomicReference<>(0);
        List<CartInfoVo.Sku> skuList = cartItems.stream().filter(cartItem -> cartItem.getSelected() && skuMap.containsKey(cartItem.getSkuId())).map(cartItem -> {
            ProductSkuRespVo productSkuRespVo = skuMap.get(cartItem.getSkuId());
            // 总价格
            totalPrice.updateAndGet(v -> v + (cartItem.getQuantity() * productSkuRespVo.getPrice()));
            // 属性值
            List<CartInfoVo.AttrValueInfo> attrValueInfos = CartConvert.INSTANCE.convertAttrList(productSkuRespVo.getAttrs());
            // sku信息
            CartInfoVo.Sku sku = CartConvert.INSTANCE.convert(productSkuRespVo);
            CartInfoVo.Spu spu = CartConvert.INSTANCE.convert(productSkuRespVo.getSpu());
            return sku.setSpu(spu).setAttrValueIds(attrValueInfos);
        }).collect(Collectors.toList());
        return new CartInfoVo().setSkuList(skuList).setFee(new CartInfoVo.Fee().setPresentTotal(totalPrice.get()));
    }

    @Override
    public void updateCartItemSelected(CartUpdateSelectDto cartUpdateSelectDto) {
        // 查询 CartItemDO 列表
        List<CartItem> itemList = cartItemMapper.selectListByUserIdAndCartItemIds(cartUpdateSelectDto.getUserId(), cartUpdateSelectDto.getCartItemIds());
        if (itemList.size() != cartUpdateSelectDto.getCartItemIds().size()) {
            throw ServiceExceptionUtil.exception(CARD_ITEM_NOT_FOUND);
        }
        // 更新选中
        cartItemMapper.updateByIdsAndUserId(cartUpdateSelectDto.getCartItemIds(), cartUpdateSelectDto.getUserId(), cartUpdateSelectDto.getSelected());
    }


    private ProductSkuRespVo checkProductSku(Integer skuId) {
        CommonResult<ProductSkuRespVo> productSpuRespVOCommonResult = productFeignService.getProductBySkuId(skuId);
        productSpuRespVOCommonResult.checkError();
        // 检查状态
        ProductSkuRespVo productSkuRespVo = productSpuRespVOCommonResult.getData();
        if (productSkuRespVo == null || CommonStatusEnum.DISABLE.getValue().equals(productSkuRespVo.getStatus())) {
            throw ServiceExceptionUtil.exception(CARD_ITEM_SKU_NOT_FOUND);
        }


        return productSkuRespVo;
    }

}
