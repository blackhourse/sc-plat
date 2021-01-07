package cn.boot.st.productservice.service;

import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.productservice.bo.ProductSpuBo;
import cn.boot.st.productservice.dto.spu.ProductSpuPageReqDTO;
import cn.boot.st.productservice.vo.spu.ProductSpuRespVO;

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
    Integer createProductSpu(ProductSpuBo productSpu);

    /**
     * 修改商品sku
     *
     * @param productSpu
     */
    void updateProductSpu(ProductSpuBo productSpu);

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
