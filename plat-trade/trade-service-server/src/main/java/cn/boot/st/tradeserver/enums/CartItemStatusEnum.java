package cn.boot.st.tradeserver.enums;

import cn.boot.common.framework.core.IntArrayValuable;

import java.util.Arrays;

/**
 * @Classname CartItemStatusEnum
 * @Description
 * @Date 2021/1/10
 * @Created by maht
 */
public enum CartItemStatusEnum implements IntArrayValuable {
    NORMAL(1, "正常"),
    ACTIVELY_DELETE(2, "主动删除"),
    ORDER_DELETE(3, "下单删除");

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(CartItemStatusEnum::getValue).toArray();

    /**
     * 类型
     */
    private final Integer value;
    /**
     * 类型名
     */
    private final String name;

    CartItemStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
