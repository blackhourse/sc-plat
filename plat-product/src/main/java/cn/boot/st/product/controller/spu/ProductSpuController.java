package cn.boot.st.product.controller.spu;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.product.controller.spu.dto.ProductSpuCreateDTO;
import cn.boot.st.product.manager.ProductSpuManager;
import io.swagger.annotations.Api;
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
 * @create: 2020-12-18
 **/
@Api(tags = "商品spu管理")
@RestController
@RequestMapping("/product-spu")
@Validated
public class ProductSpuController {
    @Autowired
    private ProductSpuManager productSpuManager;

    @PostMapping("/create")
    @ApiOperation("创建商品 SPU")
    public CommonResult<Integer> createProductSpu(@RequestBody @Valid ProductSpuCreateDTO productSpuCreateDTO) {
        return success(productSpuManager.createProductSpu(productSpuCreateDTO));
    }


}
