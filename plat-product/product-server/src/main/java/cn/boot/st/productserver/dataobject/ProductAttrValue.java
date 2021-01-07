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
 * product_attr(product_attr_value)实体类
 *
 * @author maht
 * @description
 * @since 2020-12-17 10:57:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("product_attr_value")
public class ProductAttrValue extends DeletableDO {

    /**
     * 规格编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 规格编号
     */
    private Integer attrId;
    /**
     * 名称
     */
    private String name;
    /**
     * 状态
     * <p>
     * 1-开启
     * 2-禁用
     */
    private Integer status;


}