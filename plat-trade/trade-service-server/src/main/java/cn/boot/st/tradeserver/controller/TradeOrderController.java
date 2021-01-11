package cn.boot.st.tradeserver.controller;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.tradeservice.service.order.TradeOrderService;
import cn.boot.st.tradeservice.service.order.dto.TradeOrderConfirmCreateInfoReqDto;
import cn.boot.st.tradeservice.service.order.vo.OrderConfirmCreateRespVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static cn.boot.common.framework.vo.CommonResult.success;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-11
 **/
@RequestMapping("trade-order")
@RestController
@Validated
public class TradeOrderController {

    @Autowired
    private TradeOrderService tradeOrderService;

    @PostMapping("confirm-create-order-info")
    @ApiOperation("基于商品，确认创建订单")
    public CommonResult<OrderConfirmCreateRespVo> getTradeOrderConfirmCreateInfo(@RequestBody @Valid TradeOrderConfirmCreateInfoReqDto tradeOrderConfirmCreateInfoReqDto) {
        return success(tradeOrderService.getOrderConfirmCreateInfo(tradeOrderConfirmCreateInfoReqDto));
    }

}
