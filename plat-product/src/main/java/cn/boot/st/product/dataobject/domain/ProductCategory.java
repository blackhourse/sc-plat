package cn.boot.st.product.dataobject.domain;

import cn.boot.st.mybatis.core.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 商品分类(product_category)实体类
 *
 * @author maht
 * @description
 * @since 2020-12-17 10:51:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("product_category")
public class ProductCategory extends DeletableDO {

    /**
     * 分类编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 父分类编号
     */
    private Integer pid;
    /**
     * 名称
     */
    private String name;
    /**
     * description
     */
    private String description;
    /**
     * picUrl
     */
    private String picUrl;
    /**
     * sort
     */
    private Integer sort;

    /**
     * 状态
     */
    private Integer status;

}