package com.mht.sc.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SysUserPwdDto {

    private Long id;

    private String oldPassword;

    private String newPassword;

}
