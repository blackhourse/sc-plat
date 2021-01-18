package cn.boot.st.securityserver.constants;

import cn.boot.common.framework.exception.util.ErrorCode;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-18
 **/
public interface SecurityServerConstants {
    /**
     * OAUTH2 模块
     */
    ErrorCode OAUTH2_UNKNOWN = new ErrorCode(1001001000, "未知错误");
    /**
     *  1001001001 ~ 1001001099 错误码，方便前端
     */
    ErrorCode CLIENT_AUTHENTICATION_FAILED = new ErrorCode(1001001001, "客户端认证失败");
    ErrorCode CLIENT_NOT_EMPTY = new ErrorCode(1001001002, "客户端ID不能为空");
    ErrorCode USERNAME_OR_PASSWORD_ERROR = new ErrorCode(1001001003, "用户名或密码错误");
    ErrorCode SYSTEM_EXECUTION_ERROR = new ErrorCode(1001001004, "系统执行出错");


}
