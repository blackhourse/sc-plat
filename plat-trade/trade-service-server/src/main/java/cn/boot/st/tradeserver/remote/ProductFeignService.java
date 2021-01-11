package cn.boot.st.tradeserver.remote;

import cn.boot.common.framework.constant.ServiceNameConstants;
import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.productservice.dto.sku.ProductSkuListQueryReqDto;
import cn.boot.st.productservice.vo.sku.ProductSkuRespVo;
import cn.boot.st.productservice.vo.spu.ProductSpuRespVO;
import cn.boot.st.tradeserver.remote.fallback.ProductFeignServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-07
 **/
@FeignClient(value = ServiceNameConstants.PRODUCT_SERVER_SERVICE, fallbackFactory = ProductFeignServiceFallbackFactory.class)
public interface ProductFeignService {

    @GetMapping("/product-sku/info")
    CommonResult<ProductSkuRespVo> getProductBySkuId(@RequestParam("productSpuId") Integer productSpuId);

    @PostMapping("/product-sku/info-list")
    CommonResult<List<ProductSkuRespVo>> getSkuInfoList(@RequestBody ProductSkuListQueryReqDto requestDto);

    @PostMapping("/product-spu/list")
    CommonResult<List<ProductSpuRespVO>> getSpuInfoList(@RequestParam("productSpuIds") Set<Integer> productSpuIds);


}
