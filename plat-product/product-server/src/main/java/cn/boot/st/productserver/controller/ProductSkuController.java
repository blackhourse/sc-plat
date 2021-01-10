package cn.boot.st.productserver.controller;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.productservice.service.ProductSkuService;
import cn.boot.st.productservice.vo.sku.ProductSkuRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static cn.boot.common.framework.vo.CommonResult.success;

/**
 * @Classname ProductSpuController
 * @Description
 * @Date 2021/1/8
 * @Created by maht
 */
@Api(tags = "商品sku")
@RestController
@RequestMapping("/product-sku")
@Validated
public class ProductSkuController {

    @Autowired
    private ProductSkuService productSkuService;

    @GetMapping("/info")
    @ApiOperation(value = "获取信息")
    public CommonResult<ProductSkuRespVo> getSkuInfo(@RequestParam Integer productSpuId) {
        return success(productSkuService.getSkuInfo(productSpuId));
    }


    @PostMapping("/info-list")
    @ApiOperation(value = "获取sku list")
    public CommonResult<List<ProductSkuRespVo>> skuInfoList(@RequestParam Set<Integer> skuIds) {
        return success(productSkuService.skuInfoList(skuIds));
    }


}
