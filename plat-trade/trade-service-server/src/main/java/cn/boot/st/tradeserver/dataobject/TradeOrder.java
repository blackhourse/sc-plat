package cn.boot.st.tradeserver.dataobject;

import cn.boot.st.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-12
 **/

@ApiModel(value = "cn-boot-st-tradeserver-dataobject-TradeOrder")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TradeOrder extends BaseDO implements Serializable {
    @ApiModelProperty(value = "")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private Integer userId;

    /**
     * 订单编号（业务）
     */
    @ApiModelProperty(value = "订单编号（业务）")
    private String orderNo;

    /**
     * 1:待付款，2:等待发货,3:已发货,4:已完成,5:已关闭
     */
    @ApiModelProperty(value = "1:待付款，2:等待发货,3:已发货,4:已完成,5:已关闭")
    private Integer orderStatus;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 订单结束时间
     */
    @ApiModelProperty(value = "订单结束时间")
    private Date orderEndTime;

    /**
     * 购买（商品）总金额，单位：分
     */
    @ApiModelProperty(value = "购买（商品）总金额，单位：分")
    private Integer buyPrice;

    /**
     * 优惠总金额，单位：分
     */
    @ApiModelProperty(value = "优惠总金额，单位：分")
    private Integer discountPrice;

    /**
     * 物流金额 (分)
     */
    @ApiModelProperty(value = "物流金额 (分)")
    private Integer logisticsPrice;

    /**
     * 最终金额，单位：分
     */
    @ApiModelProperty(value = "最终金额，单位：分")
    private Integer presentPrice;

    /**
     * 实际已支付金额，单位：分,初始时，金额为 0 。等到支付成功后，会进行更新。
     */
    @ApiModelProperty(value = "实际已支付金额，单位：分,初始时，金额为 0 。等到支付成功后，会进行更新。")
    private Integer payPrice;

    /**
     * 退款金额，单位：分
     */
    @ApiModelProperty(value = "退款金额，单位：分")
    private Integer refundPrice;

    /**
     * 付款时间
     */
    @ApiModelProperty(value = "付款时间")
    private Date payTime;

    /**
     * 支付订单编号
     */
    @ApiModelProperty(value = "支付订单编号")
    private Integer payTransactionId;

    /**
     * 支付成功的支付渠道
     */
    @ApiModelProperty(value = "支付成功的支付渠道")
    private Integer payChannel;

    /**
     * 配送类型：0无需快递，1：传统快递
     */
    @ApiModelProperty(value = "配送类型：0无需快递，1：传统快递")
    private Integer deliveryType;

    /**
     * 发货时间
     */
    @ApiModelProperty(value = "发货时间")
    private Date deliveryTime;

    /**
     * 收货时间
     */
    @ApiModelProperty(value = "收货时间")
    private Date receiveTime;

    /**
     * 收货人
     */
    @ApiModelProperty(value = "收货人")
    private String receiverName;

    /**
     * 收货人手机号
     */
    @ApiModelProperty(value = "收货人手机号")
    private String receiverMobile;

    /**
     * 收件人地区编码
     */
    @ApiModelProperty(value = "收件人地区编码")
    private Integer receiverAreaCode;

    /**
     * 收件人详细地址
     */
    @ApiModelProperty(value = "收件人详细地址")
    private String receiverDetailAddress;

    /**
     * 售后状态0:无 1:售后中，2:已结束
     */
    @ApiModelProperty(value = "售后状态0:无 1:售后中，2:已结束")
    private Integer afterSaleStatus;

    /**
     * 优惠劵编号
     */
    @ApiModelProperty(value = "优惠劵编号")
    private Integer couponCardId;

    private static final long serialVersionUID = 1L;
}