package cn.boot.st.tradeserver.enums;

import lombok.Getter;

/**
 * 交易订单 - 状态蜜桔
 *
 * @author Sin
 */
@Getter
public enum TradeOrderStatusEnum {

    WAITING_PAYMENT(1, "等待付款"),
    WAIT_SHIPMENT(2, "等待发货"),
    ALREADY_SHIPMENT(3, "已发货"),
    COMPLETED(3, "已完成"),
    CLOSED(4, "已关闭");

    /**
     * 状态值
     */
    private final Integer value;
    /**
     * 状态名
     */
    private final String name;

    TradeOrderStatusEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

}

