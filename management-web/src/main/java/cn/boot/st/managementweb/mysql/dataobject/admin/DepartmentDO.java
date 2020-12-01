package cn.boot.st.managementweb.mysql.dataobject.admin;

import cn.boot.st.mybatis.core.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 部门实体
 */
@TableName(value = "admin_department")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class DepartmentDO extends DeletableDO {

    /**
     * 部门编号
     */
    private Integer id;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 排序值
     */
    private Integer sort;
    /**
     * 父级部门编号
     * <p>
     * 外键 {@link #id}
     */
    private Integer pid;

}
