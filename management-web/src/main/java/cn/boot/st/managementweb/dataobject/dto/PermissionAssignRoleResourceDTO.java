package cn.boot.st.managementweb.dataobject.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @Classname PermissionAssignRoleResourceDTO
 * @Description
 * @Date 2020/12/5
 * @Created by maht
 */
@ApiModel("赋予角色资源 DTO")
@Data
public class PermissionAssignRoleResourceDTO {

    @ApiModelProperty(value = "角色名", required = true, example = "1")
    @NotNull(message = "角色编号不能为空")
    private Integer roleId;
    @ApiModelProperty(value = "资源编号列表", example = "1,3,5")
    private Set<Integer> resourceIds;

}
