package com.mht.sc.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SysMenuAddOrUpdateDto {
    private Long id;

    private Long parentId;

    private String name;

    private String url;

    private String path;

    private String pathMethod;

    private String css;

    private Integer sort;
    /**
     * 1:一级菜单 2二级菜单
     */
    private Integer type;
    /**
     * 0显示 1隐藏
     */
    private Integer hidden;
    /**
     * 租户字段
     */
    private String tenantId;
}
