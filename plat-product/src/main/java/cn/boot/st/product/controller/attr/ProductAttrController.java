package cn.boot.st.product.controller.attr;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.product.controller.attr.dto.*;
import cn.boot.st.product.controller.attr.vo.ProductAttrKeyVO;
import cn.boot.st.product.controller.attr.vo.ProductAttrValueRespVO;
import cn.boot.st.product.service.ProductAttrService;
import cn.boot.st.security.annotations.RequiresPermissions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static cn.boot.common.framework.vo.CommonResult.success;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-17
 **/
@Api(tags = "商品规格管理")
@RestController
@RequestMapping("/product-attr")
@Validated
public class ProductAttrController {

    @Autowired
    private ProductAttrService productAttrService;

    @PostMapping("/key/create")
    @ApiOperation(value = "添加规格key")
    @RequiresPermissions("product-attr:key:create")
    public CommonResult<Integer> createProductAttrKey(@Valid ProductAttrKeyCreateDTO productAttrKeyCreateDTO) {
        return success(productAttrService.createProductAttrKey(productAttrKeyCreateDTO));
    }

    @GetMapping("/key/info")
    @ApiOperation(value = "获取规格key")
    @RequiresPermissions("product-attr:key:info")
    public CommonResult<ProductAttrKeyVO> getProductAttrKeyInfo(Integer attrId) {
        return success(productAttrService.getProductAttrKeyInfo(attrId));
    }

    @GetMapping("/key/update")
    @ApiOperation(value = "更新规格key")
    @RequiresPermissions("product-attr:key:update")
    public CommonResult<String> updateProductAttrKey(@Valid ProductAttrKeyUpdateDTO productAttrKeyUpdateDTO) {
        productAttrService.updateProductAttrKey(productAttrKeyUpdateDTO);
        return success();
    }

    @GetMapping("/key/page")
    @ApiOperation("获得商品规格键分页")
    @RequiresPermissions("product-attr:key:page")
    public CommonResult<PageResult<ProductAttrKeyVO>> pageProductAttrKey(@Valid ProductAttrKeyPageReqDTO pageVO) {
        return success(productAttrService.pageProductAttrKey(pageVO));
    }


    @PostMapping("/value/create")
    @ApiOperation(value = "添加规格value")
    @RequiresPermissions("product-attr:value:create")
    public CommonResult<Integer> createProductAttrValue(@Valid ProductAttrValueCreateDTO productAttrValueCreateDTO) {
        return success(productAttrService.createAttrValue(productAttrValueCreateDTO));
    }

    @PostMapping("/value/update")
    @ApiOperation(value = "修改规格value")
    @RequiresPermissions("product-attr:value:update")
    public CommonResult<String> updateAttrValue(@Valid ProductAttrValueUpdateDTO productAttrValueUpdateDTO) {
        productAttrService.updateAttrValue(productAttrValueUpdateDTO);
        return success();
    }

    @GetMapping("/value/get")
    @ApiOperation("获得商品规格值")
    @ApiImplicitParam(name = "productAttrValueId", value = "商品规格值编号", required = true)
    @RequiresPermissions("product-attr:value:get")
    public CommonResult<ProductAttrValueRespVO> getProductAttrValue(@RequestParam("productAttrValueId") Integer productAttrValueId) {
        return success(productAttrService.getProductAttrValue(productAttrValueId));
    }

    @GetMapping("/value/list")
    @ApiOperation("获得商品规格值列表")
    @RequiresPermissions("product-attr:value:list")
    public CommonResult<List<ProductAttrValueRespVO>> listProductAttrValues(@Valid ProductAttrValueListQueryReqDTO queryReqDTO) {
        return success(productAttrService.listProductAttrValues(queryReqDTO));
    }

}
