package cn.boot.st.productservice.vo.attr;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-17
 **/
@Data
@ApiModel("商品规格key vo")
public class ProductAttrKeyVO {
    private Integer id;

    @ApiModelProperty(value = "规格名称")
    private String name;

    @ApiModelProperty(value = "规格状态")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
