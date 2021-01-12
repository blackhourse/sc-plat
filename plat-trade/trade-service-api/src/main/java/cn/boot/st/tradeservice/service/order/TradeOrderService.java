package cn.boot.st.tradeservice.service.order;

import cn.boot.st.tradeservice.service.dto.TradeOrderConfirmCreateInfoReqDto;
import cn.boot.st.tradeservice.service.dto.TradeOrderCreateReqDto;
import cn.boot.st.tradeservice.service.vo.OrderConfirmCreateRespVo;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-06
 **/
public interface TradeOrderService {

    /**
     * 基于商品获取创建订单信息
     *
     * @param tradeOrderConfirmCreateInfoReqDto
     * @return
     */
    OrderConfirmCreateRespVo getOrderConfirmCreateInfo(TradeOrderConfirmCreateInfoReqDto tradeOrderConfirmCreateInfoReqDto);

    /**
     * 基于购物车获取创建订单信息
     *
     * @param userId
     * @param couponCardId
     */
    OrderConfirmCreateRespVo getOrderConfirmCreateInfoFromCart(Integer userId, Integer couponCardId);

    /**
     * 基于商品创建订单
     *
     * @param createReqDto
     * @return
     */
    Integer createTradeOrder(TradeOrderCreateReqDto createReqDto);

}
