package cn.boot.st.tradeservice.service.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-11
 **/
@Data
@Accessors(chain = true)
@ApiModel("基于商品创建订单确认信息 dto")
public class TradeOrderConfirmCreateInfoReqDto {
    @NotNull
    private Integer userId;
    @NotNull
    private Integer skuId;
    @NotNull
    private Integer quantity;
}
