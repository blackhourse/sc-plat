package cn.boot.st.tradeservice.service.cart.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @Classname CartUpdateSelectDto
 * @Description
 * @Date 2021/1/10
 * @Created by maht
 */
@Data
@Accessors(chain = true)
@ApiModel("更新购物车 商品数量 DTO")
public class CartUpdateSelectDto {


    @Size(min = 1, message = "商品编号不能为空")
    private Set<Integer> cartItemIds;
    @NotNull(message = "选中状态不能为空")
    private Boolean selected;
    @NotNull(message = "用户编号不能为空")
    private Integer userId;
}
