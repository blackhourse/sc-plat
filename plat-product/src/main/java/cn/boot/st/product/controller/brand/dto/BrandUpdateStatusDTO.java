package cn.boot.st.product.controller.brand.dto;

import cn.boot.common.framework.enums.CommonStatusEnum;
import cn.boot.common.framework.validator.InEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-15
 **/
@Data
public class BrandUpdateStatusDTO {
    /**
     * 品牌编号（主键）
     */
    @NotNull(message = "记录id不能为空")
    private Long id;
    /**
     * 状态 1开启 2禁用
     */
    @ApiModelProperty(value = "状态 1开启 2禁用", required = true, example = "1")
    @NotNull(message = "状态 1开启 2禁用不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;
}
