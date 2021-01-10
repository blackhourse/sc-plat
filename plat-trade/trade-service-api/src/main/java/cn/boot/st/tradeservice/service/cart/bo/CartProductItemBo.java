package cn.boot.st.tradeservice.service.cart.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Classname CartProductItenBo
 * @Description
 * @Date 2021/1/9
 * @Created by maht
 */
@Data
@Accessors(chain = true)
public class CartProductItemBo {
    /**
     * SKU 编号
     */
    @NotNull(message = "商品 SKU 编号不能为空")
    private Integer skuId;
    /**
     * 数量
     */
    @NotNull(message = "商品 SKU 数量不能为空")
    private Integer quantity;

    @ApiModelProperty(value = "订单编号")
    private Integer attrValueId;

    /**
     * 是否选中
     */
    @NotNull(message = "是否选中不能为空")
    private Boolean selected;

}
