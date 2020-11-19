package com.mht.sc.scadmin.dto;

import com.mht.common.api.PageEntity;
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
