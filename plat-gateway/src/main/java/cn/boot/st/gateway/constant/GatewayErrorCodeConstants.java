package cn.boot.st.gateway.constant;


import cn.boot.common.framework.exception.util.ErrorCode;

/**
 * @author mht
 */
public interface GatewayErrorCodeConstants {


    /**
     * 1-007-000-000 ~ 1-008-000-000
     */

    ErrorCode TOKEN_INVALID_OR_EXPIRED = new ErrorCode(1007001001, "token无效或已过期");
    ErrorCode USER_ACCESS_UNAUTHORIZED = new ErrorCode(1007001002, "访问未授权");
    ErrorCode SYSTEM_EXECUTION_ERROR = new ErrorCode(1007001003, "系统执行出错");


}
