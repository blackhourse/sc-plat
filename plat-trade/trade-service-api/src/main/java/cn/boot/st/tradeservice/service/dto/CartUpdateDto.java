package cn.boot.st.tradeservice.service.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Classname CartUpdateDto
 * @Description
 * @Date 2021/1/9
 * @Created by maht
 */
@Data
@Accessors(chain = true)
public class CartUpdateDto {

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Integer userId;
    /**
     * 商品 SKU 编号
     */
    @NotNull(message = "商品 SKU 编号不能为空")
    private Integer skuId;

    /**
     * 数量
     */
    @NotNull(message = "数量不能为空")
    @Min(message = "数量必须大于 0", value = 1L)
    private Integer quantity;
}
