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
 * product_sku(product_sku)实体类
 *
 * @author maht
 * @description
 * @since 2020-12-17 10:57:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("product_sku")
public class ProductSku extends DeletableDO {

    /**
     * sku 编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 商品编号
     */
    private Integer spuId;
    /**
     * 状态
     * <p>
     * 1-正常
     * 2-禁用
     */
    private Integer status;
    /**
     * 图片地址
     */
    private String picUrl;
    /**
     * 规格值({@link ProductAttr})数组
     * <p>
     * 数组，以逗号分隔
     */
    private String attrs;
    /**
     * 价格，单位：分
     */
    private Integer price;
    /**
     * 库存数量
     */
    private Integer quantity;


}