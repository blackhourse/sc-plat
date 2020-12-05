package cn.boot.st.managementweb.dataobject.dto;

import cn.boot.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname RolePageDTO
 * @Description
 * @Date 2020/12/5 19:11
 * @Created by maht
 */
@ApiModel("角色分页 DTO")
@Data
public class RolePageDTO extends PageParam {

    @ApiModelProperty(value = "角色名", example = "管理", notes = "模糊匹配")
    private String name;

}
