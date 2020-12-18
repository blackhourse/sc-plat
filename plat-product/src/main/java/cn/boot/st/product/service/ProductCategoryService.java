package cn.boot.st.product.service;

import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.product.controller.category.dto.ProductCategoryCreateDTO;
import cn.boot.st.product.controller.category.dto.ProductCategoryPageReqDTO;
import cn.boot.st.product.controller.category.dto.ProductCategoryUpdateDTO;
import cn.boot.st.product.controller.category.vo.ProductCategoryRespVO;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-17
 **/
public interface ProductCategoryService {
    /**
     * 创建商品分类
     *
     * @param productCategoryCreateDTO
     * @return
     */
    Integer createProductCategory(ProductCategoryCreateDTO productCategoryCreateDTO);

    /**
     * 修改商品分类
     *
     * @param productCategoryUpdateDTO
     */
    void updateProductCategory(ProductCategoryUpdateDTO productCategoryUpdateDTO);

    /**
     * 删除分类
     *
     * @param categoryId
     */
    void deleteProductCategory(Integer categoryId);

    /**
     * 根据id获取分类信息
     *
     * @param categoryId
     * @return
     */
    ProductCategoryRespVO getProductCategory(Integer categoryId);

    /**
     * 获取分类列表
     *
     * @param categoryIds
     * @return
     */
    List<ProductCategoryRespVO> getProductCategoryList(List<Integer> categoryIds);

    /**
     * 分类列表-分页
     *
     * @param productCategoryPageReqDTO
     * @return
     */
    PageResult<ProductCategoryRespVO> pageProductCategory(ProductCategoryPageReqDTO productCategoryPageReqDTO);

}
