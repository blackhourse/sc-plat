package cn.boot.st.tradeserver.enums;

import lombok.Getter;

/**
 * 物流的配送类型
 */
@Getter
public enum LogisticsDeliveryTypeEnum {

    /**
     * 无需快递
     */
    NULL(0),
    /**
     * 传统快递
     */
    EXPRESS(1);

    private final Integer deliveryType;

    LogisticsDeliveryTypeEnum(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

}
