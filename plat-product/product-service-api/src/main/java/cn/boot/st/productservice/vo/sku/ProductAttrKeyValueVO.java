package cn.boot.st.productservice.vo.sku;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname ProductAttrKeyValueBO
 * @Description
 * @Date 2020/12/20
 * @Created by maht
 */
@Data
@Accessors(chain = true)
public class ProductAttrKeyValueVO {
    /**
     * 规格 Key 编号
     */
    private Integer attrKeyId;
    /**
     * 规格 Key 名字
     */
    private String attrKeyName;
    /**
     * 规格 Value 编号
     */
    private Integer attrValueId;
    /**
     * 规格 Value 名字
     */
    private String attrValueName;
}
