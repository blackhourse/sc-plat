package cn.boot.common.framework.dataobject.vo;

import cn.boot.common.framework.enums.admin.AdminStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-30
 **/
@Data
@Accessors(chain = true)
public class AdminUserTokenInfo implements Serializable {
    /**
     * 管理员编号
     */
    private Integer id;
    /**
     * 真实名字
     */
    private String name;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 部门编号
     * <p>
     * 关联 {@link DepartmentDO#getId()}
     */
    private Integer departmentId;
    /**
     * 在职状态
     * <p>
     * 枚举 {@link AdminStatusEnum}
     */
    private Integer status;

    /**
     * 登陆账号
     */
    private String username;
    /**
     * 经过加密的密码串
     */
    private String password;
    /**
     * {@link #password} 的盐
     */
    private String passwordSalt;

    /**
     * 创建管理员编号
     * <p>
     * 外键 {@link AdminDO#id}
     */
    private Integer createAdminId;
    /**
     * 创建 IP
     */
    private String createIp;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date updateTime;

    @ApiModelProperty(value = "访问令牌", required = true, example = "001e8f49b20e47f7b3a2de774497cd50")
    private String accessToken;
    @ApiModelProperty(value = "刷新令牌", required = true, example = "001e8f49b20e47f7b3a2de774497cd50")
    private String refreshToken;


}
