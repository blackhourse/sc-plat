package cn.boot.common.framework.enums;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-29
 **/
public enum SystemClientType {


    MANAGEMENT_USER("web", "后台管理用户"),
    APP_USER("app", "app用户");


    /**
     * 类型
     */
    private final String value;
    /**
     * 类型名
     */
    private final String name;

    SystemClientType(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public static SystemClientType getInstance(String value) {
        for (SystemClientType t : SystemClientType.values()) {
            if (value.equals(t.value)) {
                return t;
            }
        }
        return null;
    }


}
