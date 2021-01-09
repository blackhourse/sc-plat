package cn.boot.st.productservice.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-18
 **/
@Data
@Accessors(chain = true)
public class ProductSkuCreateOrUpdateBO {

    @ApiModelProperty(value = "sku id", required = true)
    private Integer id;

    /**
     * 规格值数组
     */
    @NotNull(message = "规格值数组不能为空")
    private List<Integer> attrValueIds;
    /**
     * 价格，单位：分
     */
    @NotNull(message = "价格不能为空")
    @Min(value = 1L, message = "最小价格为 1")
    private Integer price;
    /**
     * 库存数量
     */
    @NotNull(message = "库存数量不能为空")
    @Min(value = 1L, message = "最小库存为 1")
    private Integer quantity;

}