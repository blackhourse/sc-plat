package com.mht.sc.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SysRoleAddOrUpdateDto {
    private Long id;

    /**
     * 角色code
     */
    private String code;

    /**
     * 角色名
     */
    private String name;

    /**
     * 租户字段
     */
    @TableField("tenant_id")
    private String tenantId;


}
