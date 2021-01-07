package cn.boot.st.productservice.vo.spu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@ApiModel("商品 SPU Response VO")
@Data
public class ProductSpuRespVO {

    @ApiModelProperty(value = "SPU 编号")
    private Integer id;
    @ApiModelProperty(value = "SPU 名字")
    private String name;
    @ApiModelProperty(value = "卖点")
    private String sellPoint;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "分类编号")
    private Integer cid;
    @ApiModelProperty(value = "商品主图地址")
    private List<String> picUrls;
    @ApiModelProperty(value = "是否上架商品")
    private Integer visible;
    @ApiModelProperty(value = "排序字段")
    private Integer sort;
    @ApiModelProperty(value = "价格")
    private Integer price;
    @ApiModelProperty(value = "库存数量")
    private Integer quantity;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
