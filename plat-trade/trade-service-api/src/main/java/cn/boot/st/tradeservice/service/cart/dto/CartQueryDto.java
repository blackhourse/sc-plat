package cn.boot.st.tradeservice.service.cart.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-07
 **/
@Data
@Accessors(chain = true)
public class CartQueryDto {

    @ApiModelProperty(value = "userId")
    @NotNull
    private Integer userId;

    @ApiModelProperty(value = "是否选中")
    private Boolean selected;
}
