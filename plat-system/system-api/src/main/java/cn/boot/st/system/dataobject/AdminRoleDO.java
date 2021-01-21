package cn.boot.st.system.dataobject;


import cn.boot.st.mybatis.core.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * {@link AdminDO} 和 {@link RoleDO} 的关联表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("permission_admin_role")
public class AdminRoleDO extends DeletableDO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 账号编号
     *
     * 关联 {@link AdminDO#getId()}
     */
    private Integer adminId;
    /**
     * 角色编号
     *
     * 关联 {@link RoleDO#getId()}
     */
    private Integer roleId;

}
