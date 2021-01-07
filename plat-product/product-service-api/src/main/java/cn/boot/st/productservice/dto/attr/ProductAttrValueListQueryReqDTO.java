package cn.boot.st.productservice.dto.attr;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-18
 **/
@ApiModel("商品规格值的列表查询条件 Request VO")
@Data
@Accessors(chain = true)
public class ProductAttrValueListQueryReqDTO {
    @ApiModelProperty(value = "商品规格值编号列表", example = "1, 3")
    private List<Integer> productAttrValueIds;

    @ApiModelProperty(value = "规格键编号", required = true, example = "2")
    private Integer productAttrKeyId;

}
