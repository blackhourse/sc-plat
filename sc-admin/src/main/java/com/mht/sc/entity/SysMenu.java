package com.mht.sc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (SysMenu)实体类
 *
 * @author maht
 * @since 2020-11-17 17:50:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 185133415373688607L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long parentId;

    private String name;

    private String url;

    private String path;

    private String pathMethod;

    private String css;

    private Integer sort;

    private Date createTime;

    private Date updateTime;
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

    @TableField(exist = false)
    private List<SysMenu> subMenus;


}