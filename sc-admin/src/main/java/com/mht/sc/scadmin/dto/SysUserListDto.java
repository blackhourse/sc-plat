package com.mht.sc.scadmin.dto;

import com.mht.sc.scadmin.util.PageEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
//@AllArgsConstructor
@Accessors(chain = true)
public class SysUserListDto extends PageEntity {

    private String userName;

    private String phone;

    private Integer enabled;

    private Integer sex;

}
