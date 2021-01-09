package cn.boot.st.productserver.controller;

import cn.boot.common.framework.vo.CommonResult;

import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.productservice.dto.category.ProductCategoryCreateDTO;
import cn.boot.st.productservice.dto.category.ProductCategoryPageReqDTO;
import cn.boot.st.productservice.dto.category.ProductCategoryUpdateDTO;
import cn.boot.st.productservice.service.ProductCategoryService;
import cn.boot.st.productservice.vo.category.ProductCategoryRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static cn.boot.common.framework.vo.CommonResult.success;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-17
 **/
@Api(tags = "商品分类管理")
@RestController
@RequestMapping("/product-category")
@Validated
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @ApiOperation(value = "添加分类")
    @PostMapping(value = "create")
    public CommonResult<Integer> createProductCategory(@Valid ProductCategoryCreateDTO productCategoryCreateDTO) {
        return success(productCategoryService.createProductCategory(productCategoryCreateDTO));
    }


    @ApiOperation(value = "修改分类")
    @PostMapping(value = "update")
    public CommonResult<String> updateProductCategory(@Valid ProductCategoryUpdateDTO productCategoryCreateDTO) {
        productCategoryService.updateProductCategory(productCategoryCreateDTO);
        return success();
    }

    @ApiOperation(value = "删除分类")
    @PostMapping(value = "delete")
    public CommonResult<String> deleteProductCategory(Integer categoryId) {
        productCategoryService.deleteProductCategory(categoryId);
        return success();
    }


    @ApiOperation(value = "获取分类信息")
    @PostMapping(value = "info")
    public CommonResult<ProductCategoryRespVO> getProductCategory(Integer categoryId) {
        return success(productCategoryService.getProductCategory(categoryId));
    }

    @ApiOperation(value = "获取分类信息列表")
    @PostMapping(value = "list")
    public CommonResult<List<ProductCategoryRespVO>> getProductCategoryList(@RequestParam("categoryIds") List<Integer> categoryIds) {
        return success(productCategoryService.getProductCategoryList(categoryIds));
    }

    @ApiOperation(value = "分类信息列表-分页")
    @PostMapping(value = "page")
    public CommonResult<PageResult<ProductCategoryRespVO>> pageProductCategoryList(@Valid ProductCategoryPageReqDTO productCategoryPageReqDTO) {
        return success(productCategoryService.pageProductCategory(productCategoryPageReqDTO));
    }


}