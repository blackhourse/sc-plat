package cn.boot.st.productserver.controller;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.productservice.service.ProductSkuService;
import cn.boot.st.productservice.vo.sku.ProductSkuRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/info")
    @ApiOperation(value = "获取信息")
    public CommonResult<ProductSkuRespVo> getSkuInfo(Integer productSpuId) {
        return success(productSkuService.getSkuInfo(productSpuId));
    }


    @PostMapping("/info-list")
    @ApiOperation(value = "获取sku list")
    public CommonResult<ProductSkuRespVo> skuInfoList(@Length(min = 1, message = "sku id 不能为空") Set<Integer> skuIds) {
        productSkuService.skuInfoList(skuIds);
        return success();
    }




}
