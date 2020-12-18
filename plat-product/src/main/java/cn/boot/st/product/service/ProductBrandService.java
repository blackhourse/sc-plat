package cn.boot.st.product.service;

import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.product.controller.brand.dto.BrandUpdateStatusDTO;
import cn.boot.st.product.controller.brand.dto.ProductBrandCreateDTO;
import cn.boot.st.product.controller.brand.dto.ProductBrandPageReqDTO;
import cn.boot.st.product.controller.brand.dto.ProductBrandUpdateDTO;
import cn.boot.st.product.controller.brand.vo.ProductBrandRespVO;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-14
 **/
public interface ProductBrandService {
    /**
     * 添加品牌
     *
     * @param productBrandCreateDTO
     * @return
     */
    Long createProductBrandDTO(ProductBrandCreateDTO productBrandCreateDTO);

    /**
     * 修改品牌
     *
     * @param productBrandUpdateDTO
     */
    void updateProductBrandDTO(@Valid ProductBrandUpdateDTO productBrandUpdateDTO);

    /**
     * 根据获取品牌详情
     *
     * @param id
     * @return
     */
    ProductBrandRespVO getInfoByBrandId(Long id);

    /**
     * 修改品牌状态
     *
     * @param brandUpdateStatusDTO
     * @return
     */
    Boolean updateBrandStatus(BrandUpdateStatusDTO brandUpdateStatusDTO);

    /**
     * 获取品牌信息
     * @param productBrandIds
     * @return
     */
    List<ProductBrandRespVO> listProductBrands(@RequestParam("productBrandIds") List<Integer> productBrandIds);

    /**
     * 获得商品品牌分页
      * @param pageReqDTO
     * @return
     */
    PageResult<ProductBrandRespVO> pageProductBrand(ProductBrandPageReqDTO pageReqDTO);

}
