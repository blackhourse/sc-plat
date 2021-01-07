package cn.boot.st.tradeserver.controller;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.tradeservice.service.cart.CartService;
import cn.boot.st.tradeservice.service.cart.dto.CartAddDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("add2")
    @ApiOperation(value = "添加购物车2")
    public CommonResult<String> createProductAttrKey(@RequestBody @Valid CartAddDto cartAddDto) {
        return success(cartService.addCart(cartAddDto));
    }
}
