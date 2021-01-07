package cn.boot.st.tradeserver.remote.fallback;

import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.productservice.vo.spu.ProductSpuRespVO;
import cn.boot.st.tradeserver.remote.ProductFeignService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
        return productSpuId -> {
            log.error("商品服务方法异常，异常信息如下:{}", throwable);
            throw ServiceExceptionUtil.exception(GET_PRODUCT_SKU_FAIL);
        };

    }
}
