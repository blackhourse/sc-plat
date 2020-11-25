package com.mht.sc.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
public class SysGrantedMenuRoleDto {
    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private Long roleId;
    /**
     * 菜单id
     */
    @ApiModelProperty(value = "菜单id集合")
    private Set<Long> menuIds;
}
