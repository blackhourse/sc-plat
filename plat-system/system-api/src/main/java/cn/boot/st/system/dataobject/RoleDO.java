package cn.boot.st.system.dataobject;


import cn.boot.common.framework.enums.role.RoleCodeEnum;
import cn.boot.common.framework.enums.role.RoleTypeEnum;
import cn.boot.st.mybatis.core.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 角色实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("permission_role")
public class RoleDO extends DeletableDO {

    /**
     * 角色编号
     */
    private Integer id;
    /**
     * 角色名
     */
    private String name;
    /**
     * 角色编码
     *
     * 关联 {@link RoleCodeEnum}
     */
    private String code;
    /**
     * 角色类型
     *
     * 关联 {@link RoleTypeEnum}
     */
    private Integer type;
    /**
     * 创建管理员编号
     */
    private Integer createAdminId;

}
