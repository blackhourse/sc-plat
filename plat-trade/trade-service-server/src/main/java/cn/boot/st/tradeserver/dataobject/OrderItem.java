package cn.boot.st.tradeserver.dataobject;

import cn.boot.st.mybatis.core.dataobject.BaseDO;
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

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OrderItem extends BaseDO implements Serializable {
    /**
     * id自增长
     */
    private Integer id;

    /**
     * 订单编号
     */
    private Integer orderId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 物流id
     */
    private Integer orderLogisticsId;

    /**
     * 商品id
     */
    private Integer skuId;

    /**
     * 商品名字
     */
    private String skuName;

    /**
     * 图片名字
     */
    private String skuImage;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 原始单价，单位：分
     */
    private Integer originPrice;

    /**
     * 购买单价，单位：分
     */
    private Integer buyPrice;

    /**
     * 最终价格，单位：分
     */
    private Integer presentPrice;

    /**
     * 购买总金额，单位：分
     */
    private Integer buyTotal;

    /**
     * 优惠总金额，单位：分
     */
    private Integer discountTotal;

    /**
     * 最终总金额，单位：分
     */
    private Integer presentTotal;

    /**
     * 付款时间
     */
    private Date paymentTime;

    /**
     * 发货时间
     */
    private Date deliveryTime;

    /**
     * 收货时间
     */
    private Date receiverTime;

    private Date closingTime;

    /**
     * 是否退换货
     */
    private Integer hasReturnExchange;

    /**
     * 备注
     */
    private String remark;

    /**
     * 发送方式
     */
    private Integer deliveryType;

    /**
     * 状态：0、代发货 1、已发货 2、已收货 20、换货中 21、换货成功 40、退货中 41、已退货
     */
    private Integer status;

    /**
     * 属性值编号
     */
    private Integer attrValueId;

    private static final long serialVersionUID = 1L;
}