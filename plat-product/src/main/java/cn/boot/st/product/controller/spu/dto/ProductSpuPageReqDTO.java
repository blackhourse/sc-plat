package cn.boot.st.product.controller.spu.dto;

import cn.boot.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel("商品 SPU分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductSpuPageReqDTO extends PageParam {

    @ApiModelProperty(value = "SPU 名字", notes = "模糊匹配", example = "艿艿")
    private String name;
    @ApiModelProperty(value = "分类编号", example = "1024")
    private Integer cid;
    @ApiModelProperty(value = "是否上架商品", example = "true")
    private Boolean visible;
    @ApiModelProperty(value = "是否有库存", example = "true")
    private Boolean hasQuantity;

}
