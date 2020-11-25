package com.mht.sc.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SysUserDto {
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

    @TableField("company")
    private String company;

    @TableField("open_id")
    private String openId;

}
