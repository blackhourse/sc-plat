package cn.boot.st.productservice.dto.spu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-18
 **/
@Data
@ApiModel("商品spu 创建 dto")
public class ProductSpuUpdateDTO {

    /**
     * Spu 编号
     */
    @NotNull(message = "SPU 编号不能为空")
    private Integer id;

    @NotNull(message = "SPU名字不能为空")
    @ApiModelProperty(value = "SPU 名字", required = true)
    private String name;

    @NotNull(message = "卖点不能为空")
    @ApiModelProperty(value = "卖点", required = true)
    private String sellPoint;

    @NotNull(message = "描述不能为空")
    @ApiModelProperty(value = "描述", required = true)
    private String description;

    @NotNull(message = "分类编号不能为空")
    @ApiModelProperty(value = "分类编号", required = true)
    private Integer cid;

    @NotNull(message = "商品主图地址不能为空")
    @ApiModelProperty(value = "商品主图地址", required = true)
    private List<String> picUrls;

    @ApiModelProperty(value = "是否上架商品1:能，0:不能", required = true, example = "1")
    @NotNull(message = "是否上架商品不能为空")
    private Integer visible;

    @ApiModelProperty(value = "排序字段", required = true)
    @NotNull(message = "排序字段不能为空1:能，0:不能")
    private Integer sort;

    @NotNull(message = "SKU 不能为空")
    @Valid
    private List<Sku> skus;

    /**
     * SKU 信息
     */
    @Data
    @Accessors(chain = true)
    public static class Sku {

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

}
