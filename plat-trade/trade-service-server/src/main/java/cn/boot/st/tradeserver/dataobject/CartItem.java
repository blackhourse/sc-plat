package cn.boot.st.tradeserver.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
* @program: sc-plat
*
*@author: maht
* @create: 2021-01-07
**/

/**
    * cart_item
    */
@ApiModel(value="cn-boot-st-tradeserver-dataobject-CartItem")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cart_item")
public class CartItem implements Serializable {
    /**
     * 编号，唯一自增。
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="编号，唯一自增。")
    private Integer id;

    /**
     * 状态
     *
     * 1-正常
     * 2-主动删除
     * 3-下单删除
     */
    @TableField(value = "status")
    @ApiModelProperty(value="状态,     *,     * 1-正常,     * 2-主动删除,     * 3-下单删除")
    private Byte status;

    /**
     * 商品在购物车中的删除时间
     */
    @TableField(value = "delete_time")
    @ApiModelProperty(value="商品在购物车中的删除时间")
    private Date deleteTime;

    /**
     * 是否选中
     */
    @TableField(value = "selected")
    @ApiModelProperty(value="是否选中")
    private Boolean selected;

    /**
     * 用户编号
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户编号")
    private Integer userId;

    /**
     * 商品 SPU 编号
     */
    @TableField(value = "spu_id")
    @ApiModelProperty(value="商品 SPU 编号")
    private Integer spuId;

    /**
     * 商品 SKU 编号
     */
    @TableField(value = "sku_id")
    @ApiModelProperty(value="商品 SKU 编号")
    private Integer skuId;

    /**
     * 商品购买数量
     */
    @TableField(value = "quantity")
    @ApiModelProperty(value="商品购买数量")
    private Integer quantity;

    /**
     * 订单编号
     */
    @TableField(value = "order_id")
    @ApiModelProperty(value="订单编号")
    private Integer orderId;

    /**
     * 规格value 编号
     */
    @TableField(value="attr_value_id")
    @ApiModelProperty(value="订单编号")
    private Integer attrValueId;


    /**
     * 订单创建时间
     */
    @TableField(value = "order_create_time")
    @ApiModelProperty(value="订单创建时间")
    private Date orderCreateTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 最后更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="最后更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_STATUS = "status";

    public static final String COL_DELETE_TIME = "delete_time";

    public static final String COL_SELECTED = "selected";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_SPU_ID = "spu_id";

    public static final String COL_SKU_ID = "sku_id";

    public static final String COL_QUANTITY = "quantity";

    public static final String COL_ORDER_ID = "order_id";

    public static final String COL_ATTR_VALUE_ID = "attr_value_id";



    public static final String COL_ORDER_CREATE_TIME = "order_create_time";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}