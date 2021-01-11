package cn.boot.st.productservice.vo.sku;

import cn.boot.st.productservice.bo.ProductAttrKeyValueBO;
import cn.boot.st.productservice.enums.ProductSkuDetailFieldEnum;
import cn.boot.st.productservice.vo.spu.ProductSpuRespVO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @Classname ProductSkuVo
 * @Description
 * @Date 2021/1/8
 * @Created by maht
 */
@Data
@Accessors(chain = true)
public class ProductSkuRespVo {

    /**
     * sku 编号
     */
    private Integer id;
    /**
     * 商品编号
     */
    private Integer spuId;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 图片地址
     */
    private String picUrl;
    /**
     * 规格值编号数组
     */
    private List<Integer> attrValueIds;
    /**
     * 价格，单位：分
     */
    private Integer price;
    /**
     * 库存数量
     */
    private Integer quantity;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 规格值数组
     *
     * 需要设置 {@link ProductSkuDetailFieldEnum#ATTR} 才返回
     */
    private List<ProductAttrKeyValueBO> attrs;
    /**
     * 商品 SPU 信息
     *
     * 需要设置 {@link ProductSkuDetailFieldEnum#SPU} 才返回
     *
     */
    private ProductSpuRespVO spu;

}
