package cn.boot.common.framework.enums.role;

/**
 * Resource 编号枚举
 */
public enum ResourceIdEnum {

    /**
     * 根节点
     */
    ROOT(0);

    private final Integer id;

    ResourceIdEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
