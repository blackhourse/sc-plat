package cn.boot.st.product.controller.category.dto;

import cn.boot.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Administrator
 */
@ApiModel("商品分类分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductCategoryPageReqDTO extends PageParam {

    @ApiModelProperty(value = "分类名称", required = false, notes = "模糊匹配", example = "1223")
    private String name;
    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "见 CommonStatusEnum 枚举")
    private Integer status;

}
