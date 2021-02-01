package cn.boot.st.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Classname DepartmentUpdateDTO
 * @Description
 * @Date 2020/12/5 16:14
 * @Created by maht
 */
@ApiModel("部门更新 DTO")
@Data
public class DepartmentUpdateDTO {

    @ApiModelProperty(value = "部门编号", required = true, example = "1")
    @NotNull(message = "部门编号不能为空")
    private Integer id;
    @ApiModelProperty(value = "部门名称", required = true, example = "技术部")
    @NotEmpty(message = "部门名称不能为空")
    private String name;
    @ApiModelProperty(value = "排序字段", required = true, example = "1024")
    @NotNull(message = "排序字段不能为空")
    private Integer sort;
    @ApiModelProperty(value = "父级部门编号", required = true, example = "2048")
    @NotNull(message = "父级部门编号不能为空")
    private Integer pid;

}