package cn.boot.st.managementweb.dataobject.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @Classname PermissionAssignAdminRoleDTO
 * @Description
 * @Date 2020/12/5
 * @Created by maht
 */
@ApiModel("赋予用户角色 DTO")
@Data
public class PermissionAssignAdminRoleDTO {

    @ApiModelProperty(value = "管理员编号", required = true, example = "1")
    @NotNull(message = "管理员编号不能为空")
    private Integer adminId;
    @ApiModelProperty(value = "角色编号列表", example = "1,3,5")
    private Set<Integer> roleIds;

}
