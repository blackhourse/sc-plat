package cn.boot.st.productserver.serviceImpl;

import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.productserver.convert.ProductSpuConvert;
import cn.boot.st.productserver.dataobject.ProductSpu;
import cn.boot.st.productserver.mapper.ProductSpuMapper;
import cn.boot.st.productservice.bo.ProductSpuBo;
import cn.boot.st.productservice.dto.spu.ProductSpuPageReqDTO;
import cn.boot.st.productservice.service.ProductSpuService;
import cn.boot.st.productservice.vo.spu.ProductSpuRespVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.boot.st.productserver.constants.ProductErrorCodeConstants.PRODUCT_SPU_NOT_EXISTS;


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
    public Integer createProductSpu(ProductSpuBo productSpuBo) {
        ProductSpu productSpu = ProductSpuConvert.INSTANCE.convert(productSpuBo);
        productSpuMapper.insert(productSpu);
        return productSpu.getId();
    }

    @Override
    public void updateProductSpu(ProductSpuBo productSpuBo) {
        // 校验spu是否存在
        if (productSpuMapper.selectById(productSpuBo.getId()) == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_SPU_NOT_EXISTS);
        }
        ProductSpu productSpu = ProductSpuConvert.INSTANCE.convert(productSpuBo);
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
