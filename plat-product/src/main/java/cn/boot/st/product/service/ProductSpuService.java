package cn.boot.st.product.service;

import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.product.controller.spu.dto.ProductSpuPageReqDTO;
import cn.boot.st.product.controller.spu.vo.ProductSpuRespVO;
import cn.boot.st.product.dataobject.domain.ProductSpu;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-18
 **/
public interface ProductSpuService {

    /**
     * 创建商品-添加spu
     *
     * @param productSpu
     * @return
     */
    Integer createProductSpu(ProductSpu productSpu);

    /**
     * 修改商品sku
     *
     * @param productSpu
     */
    void updateProductSpu(ProductSpu productSpu);

    /**
     * 获取spu
     *
     * @param productSpuId
     * @return
     */
    ProductSpuRespVO getProductSpu(Integer productSpuId);

    /**
     * 获取spu列表
     *
     * @param productSpuIds
     * @return
     */
    List<ProductSpuRespVO> listProductSpus(List<Integer> productSpuIds);


    PageResult<ProductSpuRespVO> pageProductSpu(ProductSpuPageReqDTO pageReqDTO);

}
