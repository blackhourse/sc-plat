package com.mht.sc.scadmin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class SysUserLoginPhoneDto {


    @NotBlank(message = "短信验证码不能为空")
    @ApiModelProperty(value = "短信验证码")
    private String smsCode;
    @NotBlank(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号")
    private String phone;
}
