package cn.boot.st.product.service.impl;

import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.product.controller.category.dto.ProductCategoryCreateDTO;
import cn.boot.st.product.controller.category.dto.ProductCategoryPageReqDTO;
import cn.boot.st.product.controller.category.dto.ProductCategoryUpdateDTO;
import cn.boot.st.product.controller.category.vo.ProductCategoryRespVO;
import cn.boot.st.product.convert.ProductCategoryConvert;
import cn.boot.st.product.dataobject.domain.ProductCategory;
import cn.boot.st.product.enums.ProductCategoryIdEnum;
import cn.boot.st.product.mapper.ProductCategoryMapper;
import cn.boot.st.product.service.ProductCategoryService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.boot.st.product.constant.ProductErrorCodeConstants.*;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-17
 **/
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public Integer createProductCategory(ProductCategoryCreateDTO productCategoryCreateDTO) {
        // 检查父分类
        checkParentCategory(productCategoryCreateDTO.getPid());
        ProductCategory productCategory = ProductCategoryConvert.INSTANCE.convert(productCategoryCreateDTO);
        productCategoryMapper.insert(productCategory);
        return productCategory.getId();
    }

    @Override
    public void updateProductCategory(ProductCategoryUpdateDTO productCategoryUpdateDTO) {
        checkParentCategory(productCategoryUpdateDTO.getPid());
        // 不能设置自己为父分类
        if (productCategoryUpdateDTO.getId().equals(productCategoryUpdateDTO.getPid())) {
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_NOT_SET_OWN);
        }
        // 商品分类是否存在
        ProductCategory productCategory = productCategoryMapper.selectById(productCategoryUpdateDTO.getId());
        if (productCategory == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_NOT_EXIST);
        }
        ProductCategory category = ProductCategoryConvert.INSTANCE.convert(productCategoryUpdateDTO);
        productCategoryMapper.updateById(category);
    }

    @Override
    public void deleteProductCategory(Integer categoryId) {
        // 商品分类是否存在
        ProductCategory productCategory = productCategoryMapper.selectById(categoryId);
        if (productCategory == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_NOT_EXIST);
        }
        Integer count = productCategoryMapper.selectCountChildByPid(categoryId);
        if (count > 0) {
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_CHILD_EXIST);
        }
        // todo 判断关联的是否有商品
        productCategoryMapper.deleteById(categoryId);
    }

    @Override
    public ProductCategoryRespVO getProductCategory(Integer categoryId) {
        // 商品分类是否存在
        ProductCategory productCategory = productCategoryMapper.selectById(categoryId);
        if (productCategory == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_NOT_EXIST);
        }
        return ProductCategoryConvert.INSTANCE.convert(productCategory);
    }

    @Override
    public List<ProductCategoryRespVO> getProductCategoryList(List<Integer> categoryIds) {
        List<ProductCategory> productCategories = productCategoryMapper.selectBatchIds(categoryIds);
        return ProductCategoryConvert.INSTANCE.convert(productCategories);
    }

    @Override
    public PageResult<ProductCategoryRespVO> pageProductCategory(ProductCategoryPageReqDTO productCategoryPageReqDTO) {
        IPage<ProductCategory> productCategoryIPage = productCategoryMapper.selectPage(productCategoryPageReqDTO);
        return ProductCategoryConvert.INSTANCE.convertPage(productCategoryIPage);
    }

    /**
     * 检查夫分类
     *
     * @param pid
     */
    private void checkParentCategory(Integer pid) {
        if (!pid.equals(ProductCategoryIdEnum.ROOT.getId())) {
            ProductCategory parentCategory = productCategoryMapper.selectById(pid);
            // 父分类是否存在
            if (parentCategory == null) {
                throw ServiceExceptionUtil.exception(BRAND_PARENT_NOT_FOUNT);
            }
            //父分类是否为一级分类
            if (!parentCategory.getPid().equals(ProductCategoryIdEnum.ROOT.getId())) {
                throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_PARENT_CAN_NOT_BE_LEVEL2);
            }
        }
    }
}
