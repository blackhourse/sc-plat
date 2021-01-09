package cn.boot.st.productservice.dto.attr;

import cn.boot.common.framework.enums.CommonStatusEnum;
import cn.boot.common.framework.validator.InEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-17
 **/
@Data
@ApiModel("规格属性添加 dto")
public class ProductAttrValueCreateDTO {

    @ApiModelProperty(value = "规格key id", required = true, example = "1")
    @NotNull(message = "规格key不能为空")
    private Integer attrId;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", required = true, example = "1")
    @NotNull(message = "规格属性名称不能为空")
    private String name;
    /**
     * 状态
     * <p>
     * 1-开启
     * 2-禁用
     */
    @ApiModelProperty(value = "规格key id", required = true, example = "1")
    @NotNull(message = "状态 1开启 2禁用不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}