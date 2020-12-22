package cn.boot.st.product.service.impl;

import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.product.controller.spu.dto.ProductSpuPageReqDTO;
import cn.boot.st.product.controller.spu.vo.ProductSpuRespVO;
import cn.boot.st.product.convert.ProductSpuConvert;
import cn.boot.st.product.dataobject.domain.ProductSpu;
import cn.boot.st.product.mapper.ProductSpuMapper;
import cn.boot.st.product.service.ProductSpuService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.boot.st.product.constant.ProductErrorCodeConstants.PRODUCT_SPU_NOT_EXISTS;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-18
 **/

@Service
public class ProductSpuServiceImpl implements ProductSpuService {

    @Autowired
    private ProductSpuMapper productSpuMapper;


    @Override
    public Integer createProductSpu(ProductSpu productSpu) {
        productSpuMapper.insert(productSpu);
        return productSpu.getId();
    }

    @Override
    public void updateProductSpu(ProductSpu productSpu) {
        // 校验spu是否存在
        if (productSpuMapper.selectById(productSpu.getId()) == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_SPU_NOT_EXISTS);
        }
        productSpuMapper.updateById(productSpu);
    }

    @Override
    public ProductSpuRespVO getProductSpu(Integer productSpuId) {
        ProductSpu productSpu = productSpuMapper.selectById(productSpuId);
        return ProductSpuConvert.INSTANCE.convert(productSpu);
    }

    @Override
    public List<ProductSpuRespVO> listProductSpus(List<Integer> productSpuIds) {
        List<ProductSpu> productSpus = productSpuMapper.selectBatchIds(productSpuIds);
        return ProductSpuConvert.INSTANCE.convertList(productSpus);
    }

    @Override
    public PageResult<ProductSpuRespVO> pageProductSpu(ProductSpuPageReqDTO pageReqDTO) {
        IPage<ProductSpu> productSpuIPage = productSpuMapper.selectPage(pageReqDTO);
        return ProductSpuConvert.INSTANCE.convertPage(productSpuIPage);

    }


}
