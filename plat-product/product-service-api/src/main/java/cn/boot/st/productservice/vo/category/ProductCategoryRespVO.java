package cn.boot.st.productservice.vo.category;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author maht
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-12-11 17:45:53
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ProductCategoryRespVO {
    /**
     * 分类编号（主键）
     */
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 父分类编号
     */
    private Integer pid;

    /**
     * description
     */
    @ApiModelProperty(value = "description")
    private String description;
    /**
     * picUrl
     */
    @ApiModelProperty(value = "picUrl")
    private String picUrl;
    /**
     * 状态 1开启 2禁用
     */
    @ApiModelProperty(value = "状态 1开启 2禁用")
    private Integer status;

    /**
     * sort
     */
    @ApiModelProperty(value = "")
    private Integer sort;


}