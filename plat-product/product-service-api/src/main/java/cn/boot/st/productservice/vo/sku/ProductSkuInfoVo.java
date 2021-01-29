package cn.boot.st.productservice.vo.sku;

import cn.boot.st.productservice.enums.ProductSkuDetailFieldEnum;
import cn.boot.st.productservice.vo.spu.ProductSpuRespVO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Classname ProductSkuVo
 * @Description
 * @Date 2021/1/8
 * @Created by maht
 */
@Data
@Accessors(chain = true)
public class ProductSkuInfoVo {

    /**
     * sku 编号
     */
    private Integer id;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 图片地址
     */
    private String picUrl;
    /**
     * 价格，单位：分
     */
    private Integer price;
    /**
     * 库存数量
     */
    private Integer quantity;

    /**
     * 规格值数组
     * <p>
     * 需要设置 {@link ProductSkuDetailFieldEnum#ATTR} 才返回
     */
    private List<ProductAttrKeyValueVO> attrs;
    /**
     * 商品 SPU 信息
     * <p>
     * 需要设置 {@link ProductSkuDetailFieldEnum#SPU} 才返回
     */
    private ProductSpuRespVO spu;

}
