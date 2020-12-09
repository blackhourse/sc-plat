package cn.boot.st.securityserver.dataobject.bo;


import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-09 16:34
 **/
@ApiModel("资源 VO")
@Data
public class RoleResourceVo {

    /**
     * 角色编号(外键：{@link RoleDO}
     */
    private Integer roleId;
    /**
     * 资源编号(外键：{@link ResourceDO}
     */
    private Integer resourceId;
}
