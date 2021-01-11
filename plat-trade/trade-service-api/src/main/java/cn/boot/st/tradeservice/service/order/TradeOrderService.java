package cn.boot.st.tradeservice.service.order;

import cn.boot.st.tradeservice.service.order.dto.TradeOrderConfirmCreateInfoReqDto;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-06
 **/
public interface TradeOrderService {

    void getOrderConfirmCreateInfo(TradeOrderConfirmCreateInfoReqDto tradeOrderConfirmCreateInfoReqDto);

}
