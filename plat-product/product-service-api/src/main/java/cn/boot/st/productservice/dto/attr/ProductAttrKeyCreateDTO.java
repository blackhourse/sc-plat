package cn.boot.st.productservice.dto.attr;

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
@ApiModel("商品规格key创建 DTO")
public class ProductAttrKeyCreateDTO {

    @ApiModelProperty(value = "规格键名称", required = true, example = "尺寸")
    @NotEmpty(message = "规格键名称不能为空")
    private String name;
    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "见 CommonStatusEnum 枚举")
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
