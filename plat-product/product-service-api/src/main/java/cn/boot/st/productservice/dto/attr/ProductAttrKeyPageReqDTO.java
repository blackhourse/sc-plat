package cn.boot.st.productservice.dto.attr;

import cn.boot.common.framework.enums.CommonStatusEnum;
import cn.boot.common.framework.validator.InEnum;
import cn.boot.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-17
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("商品规格列表分页 ")
public class ProductAttrKeyPageReqDTO extends PageParam {
    @ApiModelProperty(value = "规格键名称", required = false, example = "尺寸", notes = "模糊匹配")
    private String name;
    @ApiModelProperty(value = "状态", example = "1", notes = "见 CommonStatusEnum 枚举")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
