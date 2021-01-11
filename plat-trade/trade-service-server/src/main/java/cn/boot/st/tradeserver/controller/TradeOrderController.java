package cn.boot.st.tradeserver.controller;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.tradeservice.service.order.TradeOrderService;
import cn.boot.st.tradeservice.service.order.dto.TradeOrderConfirmCreateInfoReqDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("confirm-create-order-info")
    @ApiOperation("基于商品，确认创建订单")
    public CommonResult<Boolean> getTradeOrderConfirmCreateInfo(@RequestBody @Valid TradeOrderConfirmCreateInfoReqDto tradeOrderConfirmCreateInfoReqDto) {
        tradeOrderService.getOrderConfirmCreateInfo(tradeOrderConfirmCreateInfoReqDto);
        return success();
    }

}
