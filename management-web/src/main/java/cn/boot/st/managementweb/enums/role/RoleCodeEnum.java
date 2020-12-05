package cn.boot.st.managementweb.enums.role;

public enum RoleCodeEnum {

    SUPER_ADMIN("SUPER_ADMIN"), // 超级管理员
    ;

    /**
     * 角色编码
     */
    private final String code;

    RoleCodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
