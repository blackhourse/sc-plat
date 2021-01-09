package cn.boot.st.tradeserver.dataobject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

/** * @Classname CartItem 
    * @Description 
    * @Date 2021/1/9 
    * @Created by maht 
 */
/**
    * cart_item
    */
@ApiModel(value="cn-boot-st-securityserver-dataobject-domain-CartItem")
@Data
public class CartItem {
    /**
    * 编号，唯一自增。
    */
    @ApiModelProperty(value="编号，唯一自增。")
    private Integer id;

    /**
    * 状态
     *
     * 1-正常
     * 2-主动删除
     * 3-下单删除
    */
    @ApiModelProperty(value="状态,     *,     * 1-正常,     * 2-主动删除,     * 3-下单删除")
    private Integer status;

    /**
    * 商品在购物车中的删除时间
    */
    @ApiModelProperty(value="商品在购物车中的删除时间")
    private LocalDateTime deleteTime;

    /**
    * 是否选中
    */
    @ApiModelProperty(value="是否选中")
    private Boolean selected;

    /**
    * 用户编号
    */
    @ApiModelProperty(value="用户编号")
    private Integer userId;

    /**
    * 商品 SPU 编号
    */
    @ApiModelProperty(value="商品 SPU 编号")
    private Integer spuId;

    /**
    * 商品 SKU 编号
    */
    @ApiModelProperty(value="商品 SKU 编号")
    private Integer skuId;

    /**
    * 商品购买数量
    */
    @ApiModelProperty(value="商品购买数量")
    private Integer quantity;

    /**
    * 订单编号
    */
    @ApiModelProperty(value="订单编号")
    private Integer orderId;

    /**
    * 规格value 编号
    */
    @ApiModelProperty(value="规格value 编号")
    private Integer attrValueId;

    /**
    * 订单创建时间
    */
    @ApiModelProperty(value="订单创建时间")
    private LocalDateTime orderCreateTime;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private LocalDateTime createTime;

    /**
    * 最后更新时间
    */
    @ApiModelProperty(value="最后更新时间")
    private LocalDateTime updateTime;
}