package cn.boot.st.tradeserver.remote.fallback;

import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.productservice.vo.sku.ProductSkuRespVo;
import cn.boot.st.productservice.vo.spu.ProductSpuRespVO;
import cn.boot.st.tradeserver.remote.ProductFeignService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static cn.boot.st.tradeserver.constants.TradeErrorCodeConstants.GET_PRODUCT_SKU_FAIL;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-07
 **/
@Component
@Slf4j
public class ProductFeignServiceFallbackFactory implements FallbackFactory<ProductFeignService> {
    @Override
    public ProductFeignService create(Throwable throwable) {
        return new ProductFeignService() {

            @Override
            public CommonResult<ProductSkuRespVo> getProductBySkuId(Integer productSpuId) {
                log.error("获取sku信息失败，异常信息如下:{}", throwable);
                throw ServiceExceptionUtil.exception(GET_PRODUCT_SKU_FAIL);
            }

            @Override
            public CommonResult<List<ProductSkuRespVo>> getSkuInfoList(Set<Integer> skuIds) {
                log.error("获取sku list失败，异常信息如下:{}", throwable);
                throw ServiceExceptionUtil.exception(GET_PRODUCT_SKU_FAIL);
            }

            @Override
            public CommonResult<List<ProductSpuRespVO>> getSpuInfoList(Set<Integer> productSpuIds) {
                log.error("获取spu list失败，异常信息如下:{}", throwable);
                throw ServiceExceptionUtil.exception(GET_PRODUCT_SKU_FAIL);
            }
        };
    }

}
