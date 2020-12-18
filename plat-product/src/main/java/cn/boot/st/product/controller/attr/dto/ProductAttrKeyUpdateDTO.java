package cn.boot.st.product.controller.attr.dto;

import cn.boot.common.framework.enums.CommonStatusEnum;
import cn.boot.common.framework.validator.InEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-17
 **/
@Data
@ApiModel("商品规格key修改 DTO")
public class ProductAttrKeyUpdateDTO {

    @ApiModelProperty(value = "规格键编号", required = true, example = "1")
    @NotNull(message = "规格键编号不能为空")
    private Integer id;

    @ApiModelProperty(value = "规格键名称", required = true, example = "尺寸")
    @NotEmpty(message = "规格键名称不能为空")
    private String name;
    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "见 CommonStatusEnum 枚举")
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
