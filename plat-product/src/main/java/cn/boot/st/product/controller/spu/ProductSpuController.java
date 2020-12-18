package cn.boot.st.product.controller.spu;

import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
