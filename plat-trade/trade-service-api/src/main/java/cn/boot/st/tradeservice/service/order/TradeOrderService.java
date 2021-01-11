package cn.boot.st.tradeservice.service.order;

import cn.boot.st.tradeservice.service.order.dto.TradeOrderConfirmCreateInfoReqDto;
import cn.boot.st.tradeservice.service.order.vo.OrderConfirmCreateRespVo;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-06
 **/
public interface TradeOrderService {

    OrderConfirmCreateRespVo getOrderConfirmCreateInfo(TradeOrderConfirmCreateInfoReqDto tradeOrderConfirmCreateInfoReqDto);

}
