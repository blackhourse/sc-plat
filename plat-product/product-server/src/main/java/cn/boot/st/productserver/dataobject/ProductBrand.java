package cn.boot.st.productserver.dataobject;

import cn.boot.st.mybatis.core.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 商品品牌(product_brand)实体类
 *
 * @author maht
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-12-11 17:45:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("product_brand")
public class ProductBrand extends DeletableDO {

    /**
     * 品牌编号（主键）
     */
    @TableId(type = IdType.AUTO)
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