package cn.boot.st.product.controller.brand.dto;

import cn.boot.common.framework.enums.CommonStatusEnum;
import cn.boot.common.framework.validator.InEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-11
 **/
@Data
@ApiModel("品牌修改 DTO")
public class ProductBrandUpdateDTO {

    /**
     * 品牌编号（主键）
     */
    @NotNull(message = "记录id不能为空")
    private Long id;

    /**
     * 品牌名称
     */
    @ApiModelProperty(value = "品牌名称", required = true, example = "小米")
    @NotNull(message = "品牌名称不能为空")
    private String name;
    /**
     * 品牌描述
     */
    @ApiModelProperty(value = "品牌描述", required = true, example = "得屌丝者得天下")
    @NotNull(message = "品牌描述不能为空")
    private String description;
    /**
     * 品牌名图片
     */
    @ApiModelProperty(value = "品牌名图片", required = true, example = "www.xxxx.com")
    @NotNull(message = "品牌名图片不能为空")
    private String picUrl;
    /**
     * 状态 1开启 2禁用
     */
    @ApiModelProperty(value = "状态 1开启 2禁用", required = true, example = "1")
    @NotNull(message = "状态 1开启 2禁用不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;
}
