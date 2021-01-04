package cn.boot.st.product.controller.spu;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.product.controller.spu.dto.ProductSpuCreateDTO;
import cn.boot.st.product.controller.spu.dto.ProductSpuPageReqDTO;
import cn.boot.st.product.controller.spu.dto.ProductSpuUpdateDTO;
import cn.boot.st.product.controller.spu.vo.ProductSpuRespVO;
import cn.boot.st.product.manager.ProductSpuManager;
import cn.boot.st.product.service.ProductSpuService;
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
 * @create: 2020-12-18
 **/
@Api(tags = "商品spu管理")
@RestController
@RequestMapping("/product-spu")
@Validated
public class ProductSpuController {
    @Autowired
    private ProductSpuManager productSpuManager;

    @Autowired
    private ProductSpuService productSpuService;

    @PostMapping("/create")
    @ApiOperation("创建商品SPU")
//    @RequiresPermissions("product-spu:create")
    public CommonResult<Integer> createProductSpu(@RequestBody @Valid ProductSpuCreateDTO productSpuCreateDTO) {
        return success(productSpuManager.createProductSpu(productSpuCreateDTO));
    }

    @PostMapping("/update")
    @ApiOperation("修改商品 SPU")
//    @RequiresPermissions("product-spu:update")
    public CommonResult<Boolean> updateProductSpu(@RequestBody @Valid ProductSpuUpdateDTO productSpuUpdateDTO) {
        productSpuManager.updateProductSpu(productSpuUpdateDTO);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得商品 SPU")
//    @RequiresPermissions("product-spu:get")
    @ApiImplicitParam(name = "productSpuId", value = "商品 SPU 编号", required = true)
    public CommonResult<ProductSpuRespVO> getProductSpu(@RequestParam("productSpuId") Integer productSpuId) {
        return success(productSpuService.getProductSpu(productSpuId));
    }


    @GetMapping("/list")
    @ApiOperation("获得商品 SPU 列表")
//    @RequiresPermissions("product-spu:list")
    @ApiImplicitParam(name = "productSpuIds", value = "商品 SPU 编号列表", required = true)
    public CommonResult<List<ProductSpuRespVO>> listProductSpus(@RequestParam("productSpuIds") List<Integer> productSpuIds) {
        return success(productSpuService.listProductSpus(productSpuIds));
    }

    @GetMapping("/page")
    @ApiOperation("获得商品 SPU 分页")
//    @RequiresPermissions("product-spu:page")
    public CommonResult<PageResult<ProductSpuRespVO>> pageProductSpu(ProductSpuPageReqDTO pageReqDTO) {
        return success(productSpuService.pageProductSpu(pageReqDTO));
    }

}
