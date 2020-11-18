package com.mht.sc.scadmin.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author maht
 * @since 2020-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("nickname")
    private String nickname;

    @TableField("head_img_url")
    private String headImgUrl;

    @TableField("mobile")
    private String mobile;

    @TableField("sex")
    private Integer sex;

    @TableField("enabled")
    private Integer enabled;

    @TableField("type")
    private String type;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("company")
    private String company;

    @TableField("open_id")
    private String openId;

    @TableField("is_del")
    private Integer isDel;

    @TableField(exist = false)
    private List<SysRole> roles;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
