package cn.boot.st.tradeserver.controller;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.tradeservice.service.cart.CartService;
import cn.boot.st.tradeservice.service.cart.dto.CartAddDto;
import cn.boot.st.tradeservice.service.cart.dto.CartQueryDto;
import cn.boot.st.tradeservice.service.cart.dto.CartUpdateDto;
import cn.boot.st.tradeservice.service.cart.dto.CartUpdateSelectDto;
import cn.boot.st.tradeservice.service.cart.vo.CartInfoVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static cn.boot.common.framework.vo.CommonResult.success;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-07
 **/
@RequestMapping("cart")
@RestController
@Validated
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("add")
    @ApiOperation(value = "添加购物车")
    public CommonResult<Integer> createProductAttrKey(@RequestBody @Valid CartAddDto cartAddDto) {
        return success(cartService.addCart(cartAddDto));
    }

    @PostMapping("update-quantity")
    @ApiOperation("更新购物车商品数量")
    public CommonResult<Boolean> updateCartItemQuantity(@RequestBody @Valid CartUpdateDto cartUpdateDto) {
        cartService.updateCartItemQuantity(cartUpdateDto);
        return success(true);
    }

    @GetMapping("sum-quantity")
    @ApiOperation("查询用户在购物车中的商品数量")
    public CommonResult<Integer> sumCartItemQuantity(@RequestParam Integer userId) {
        return success(cartService.sumCartItemQuantity(userId));
    }


    @PostMapping("/get-detail")
    @ApiOperation("查询用户的购物车的商品列表")
    public CommonResult<CartInfoVo> getCartDetail(@RequestBody @Valid CartQueryDto cartQueryDto) {
        return success(cartService.getCartDetail(cartQueryDto));
    }

    @PostMapping("update-selected")
    @ApiOperation("更新购物车商品是否选中")
    public CommonResult<Boolean> updateCartItemSelected(@RequestBody @Valid CartUpdateSelectDto cartUpdateSelectDto) {
        cartService.updateCartItemSelected(cartUpdateSelectDto);
        // 获得目前购物车明细
        return success(true);
    }



}
