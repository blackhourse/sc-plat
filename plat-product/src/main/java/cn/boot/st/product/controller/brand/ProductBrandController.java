package cn.boot.st.product.controller.brand;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.product.controller.brand.dto.BrandUpdateStatusDTO;
import cn.boot.st.product.controller.brand.dto.ProductBrandCreateDTO;
import cn.boot.st.product.controller.brand.dto.ProductBrandPageReqDTO;
import cn.boot.st.product.controller.brand.dto.ProductBrandUpdateDTO;
import cn.boot.st.product.controller.brand.vo.ProductBrandRespVO;
import cn.boot.st.product.service.ProductBrandService;
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
 * @create: 2020-12-11
 **/
@Api(tags = "品牌管理")
@RestController
@RequestMapping("/product-brand")
@Validated
public class ProductBrandController {

    @Autowired
    private ProductBrandService productBrandService;

    @PostMapping("/create")
    @ApiOperation(value = "添加品牌")
//    @RequiresPermissions("product-brand:create")
    public CommonResult<Long> createProductBrand(ProductBrandCreateDTO productBrandCreateDTO) {
        return success(productBrandService.createProductBrandDTO(productBrandCreateDTO));
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改品牌")
//    @RequiresPermissions("product-brand:update")
    public CommonResult<String> updateProductBrand(ProductBrandUpdateDTO productBrandUpdateDTO) {
        productBrandService.updateProductBrandDTO(productBrandUpdateDTO);
        return success();
    }


    @PostMapping("/update-status")
    @ApiOperation(value = "修改品牌状态")
//    @RequiresPermissions("product-brand:update-status")
    public CommonResult<Boolean> updateBrandStatus(@Valid BrandUpdateStatusDTO brandUpdateStatusDTO) {
        return success(productBrandService.updateBrandStatus(brandUpdateStatusDTO));
    }

    @GetMapping("/info-id")
    @ApiOperation(value = "根据品牌id获取品牌信息")
//    @RequiresPermissions("product-brand:info-id")
    public CommonResult<ProductBrandRespVO> getBrandInfoByBrandId(Long id) {
        return success(productBrandService.getInfoByBrandId(id));
    }


    @GetMapping("/list")
    @ApiOperation("获得商品品牌列表")
    @ApiImplicitParam(name = "productBrandIds", value = "商品品牌编号列表", required = true)
//    @RequiresPermissions("product-brand:list")
    public CommonResult<List<ProductBrandRespVO>> listProductBrands(@RequestParam("productBrandIds") List<Integer> productBrandIds) {
        return success(productBrandService.listProductBrands(productBrandIds));
    }

    @GetMapping("/page")
    @ApiOperation("获得商品品牌分页")
//    @RequiresPermissions("product-brand:page")
    public CommonResult<PageResult<ProductBrandRespVO>> pageProductBrand(ProductBrandPageReqDTO pageReqDTO) {
        return success(productBrandService.pageProductBrand(pageReqDTO));
    }

}
