package com.mht.sc.scadmin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "")
public class SysUserEnableDto {

    private Long id;
    @ApiModelProperty(value = "1正常 0禁用")
    private Integer enabled;

}
