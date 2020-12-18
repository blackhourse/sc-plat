package cn.boot.st.product.controller.brand.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 商品品牌(product_brand)实体类
 *
 * @author maht
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-12-11 17:45:53
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ProductBrandRespVO {

    /**
     * 品牌编号（主键）
     */
    private Long id;
    /**
     * 品牌名称
     */
    private String name;
    /**
     * 品牌描述
     */
    private String description;
    /**
     * 品牌名图片
     */
    private String picUrl;
    /**
     * 状态 1开启 2禁用
     */
    private Integer status;


}