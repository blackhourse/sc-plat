package cn.boot.st.tradeserver.remote;

import cn.boot.common.framework.constant.ServiceNameConstants;
import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.productservice.vo.spu.ProductSpuRespVO;
import cn.boot.st.tradeserver.remote.fallback.ProductFeignServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-07
 **/
@FeignClient(value = ServiceNameConstants.PRODUCT_SERVER_SERVICE, fallbackFactory = ProductFeignServiceFallbackFactory.class)
public interface ProductFeignService {

    @GetMapping("/product-spu/get")
    CommonResult<ProductSpuRespVO> getProductBySkuId(@RequestParam("productSpuId") Integer productSpuId);


}
