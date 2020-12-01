package cn.boot.st.managementweb.mysql.dataobject.admin;


import cn.boot.st.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * 管理员实体
 * <p>
 * uk_username 索引：基于 {@link #username} 字段
 */
@TableName(value = "admin")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AdminDO extends BaseDO {

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

}
