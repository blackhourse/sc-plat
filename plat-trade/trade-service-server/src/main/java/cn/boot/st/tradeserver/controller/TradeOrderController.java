package cn.boot.st.tradeserver.controller;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.tradeservice.service.dto.TradeOrderConfirmCreateInfoReqDto;
import cn.boot.st.tradeservice.service.dto.TradeOrderCreateReqDto;
import cn.boot.st.tradeservice.service.order.TradeOrderService;
import cn.boot.st.tradeservice.service.vo.OrderConfirmCreateRespVo;
import io.swagger.annotations.ApiImplicitParam;
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
    @ApiOperation("基于商品，获取确认创建订单信息")
    public CommonResult<OrderConfirmCreateRespVo> getTradeOrderConfirmCreateInfo(@RequestBody @Valid TradeOrderConfirmCreateInfoReqDto tradeOrderConfirmCreateInfoReqDto) {
        return success(tradeOrderService.getOrderConfirmCreateInfo(tradeOrderConfirmCreateInfoReqDto));
    }

    @GetMapping("confirm-create-order-info-from-cart")
    @ApiOperation("基于购物车，获取确认创建订单信息")
    @ApiImplicitParam(name = "couponCardId", value = "优惠劵编号", example = "1")
    public CommonResult<OrderConfirmCreateRespVo> getTradeOrderConfirmCreateInfoFromCart(
            @RequestParam(value = "couponCardId", required = false) Integer couponCardId,
            @RequestParam(value = "userId", required = true) Integer userId) {
        return success(tradeOrderService.getOrderConfirmCreateInfoFromCart(userId, couponCardId));
    }

    @PostMapping("create")
    @ApiOperation("基于商品，创建订单")
    public CommonResult<Integer> createTradeOrder(@RequestBody @Valid TradeOrderCreateReqDto createReqDto) {
        return success(tradeOrderService.createTradeOrder(createReqDto));
    }


}
