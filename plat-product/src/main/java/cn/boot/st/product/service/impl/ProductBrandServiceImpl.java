package cn.boot.st.product.service.impl;

import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.product.controller.brand.dto.BrandUpdateStatusDTO;
import cn.boot.st.product.controller.brand.dto.ProductBrandCreateDTO;
import cn.boot.st.product.controller.brand.dto.ProductBrandPageReqDTO;
import cn.boot.st.product.controller.brand.dto.ProductBrandUpdateDTO;
import cn.boot.st.product.controller.brand.vo.ProductBrandRespVO;
import cn.boot.st.product.convert.ProductBrandConvert;
import cn.boot.st.product.dataobject.domain.ProductBrand;
import cn.boot.st.product.mapper.ProductBrandMapper;
import cn.boot.st.product.service.ProductBrandService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.boot.st.product.constant.ProductErrorCodeConstants.*;


/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-14
 **/
//@Service("productBrandService")
@Service
public class ProductBrandServiceImpl implements ProductBrandService {
    @Autowired
    private ProductBrandMapper productBrandMapper;

    @Override
    public Long createProductBrandDTO(ProductBrandCreateDTO productBrandCreateDTO) {
        //检查品牌是否重复
        ProductBrand productBrand = productBrandMapper.selectByName(productBrandCreateDTO.getName());
        if (productBrand != null) {
            throw ServiceExceptionUtil.exception(PRODUCT_BRAND_NAME_EXIST);
        }
        ProductBrand brand = ProductBrandConvert.INSTANCE.convert(productBrandCreateDTO);
        //插入到数据库
        productBrandMapper.insert(brand);
        return brand.getId();
    }

    @Override
    public void updateProductBrandDTO(ProductBrandUpdateDTO productBrandUpdateDTO) {
        // 校验更新的商品品牌是否存在
        ProductBrand brand = productBrandMapper.selectById(productBrandUpdateDTO.getId());
        if (brand == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_BRAND_NOT_FOUND);
        }
        // 校验商品品牌的名字是否已经使用
        ProductBrand productBrand = productBrandMapper.selectByName(productBrandUpdateDTO.getName());
        if (productBrand != null && !productBrand.getId().equals(productBrandUpdateDTO.getId())) {
            throw ServiceExceptionUtil.exception(PRODUCT_BRAND_NAME_EXIST);
        }
        //插入到数据库
        ProductBrand updateBrand = ProductBrandConvert.INSTANCE.convert(productBrandUpdateDTO);
        productBrandMapper.updateById(updateBrand);
    }

    @Override
    public ProductBrandRespVO getInfoByBrandId(Long id) {
        // 校验更新的商品品牌是否存在
        ProductBrand brand = productBrandMapper.selectById(id);
        if (brand == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_BRAND_NOT_FOUND);
        }
        return ProductBrandConvert.INSTANCE.convert(brand);
    }

    @Override
    public Boolean updateBrandStatus(BrandUpdateStatusDTO brandUpdateStatusDTO) {
        ProductBrand brand = productBrandMapper.selectById(brandUpdateStatusDTO.getId());
        if (brand == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_BRAND_NOT_FOUND);
        }
        if (brand.getStatus().equals(brandUpdateStatusDTO.getStatus())) {
            throw ServiceExceptionUtil.exception(PRODUCT_BRAND_IS_THIS);
        }

        ProductBrandConvert.INSTANCE.convert(brandUpdateStatusDTO);

        return Boolean.TRUE;
    }

    @Override
    public List<ProductBrandRespVO> listProductBrands(List<Integer> productBrandIds) {
        List<ProductBrand> productBrands = productBrandMapper.selectBatchIds(productBrandIds);
        return ProductBrandConvert.INSTANCE.convert(productBrands);
    }

    @Override
    public PageResult<ProductBrandRespVO> pageProductBrand(ProductBrandPageReqDTO pageReqDTO) {
        IPage<ProductBrand> productBrandPage = productBrandMapper.selectPage(pageReqDTO);
        return ProductBrandConvert.INSTANCE.convertPage(productBrandPage);
    }
}
