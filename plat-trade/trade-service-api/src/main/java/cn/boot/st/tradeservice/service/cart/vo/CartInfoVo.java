package cn.boot.st.tradeservice.service.cart.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-07
 **/
@Data
@Accessors(chain = true)
public class CartInfoVo {

    @ApiModelProperty(value="编号，唯一自增。")
    private Integer id;
    @ApiModelProperty(value="状态,     *,     * 1-正常,     * 2-主动删除,     * 3-下单删除")
    private Byte status;
    @ApiModelProperty(value="商品在购物车中的删除时间")
    private Date deleteTime;
    @ApiModelProperty(value="是否选中")
    private Boolean selected;
    @ApiModelProperty(value="用户编号")
    private Integer userId;
    @ApiModelProperty(value="商品 SPU 编号")
    private Integer spuId;
    @ApiModelProperty(value="商品 SKU 编号")
    private Integer skuId;
    @ApiModelProperty(value="商品购买数量")
    private Integer quantity;
    @ApiModelProperty(value="订单编号")
    private Integer orderId;
    @ApiModelProperty(value="订单创建时间")
    private Date orderCreateTime;
    @ApiModelProperty(value="创建时间")
    private Date createTime;
    @ApiModelProperty(value="最后更新时间")
    private Date updateTime;
}
