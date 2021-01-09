package cn.boot.common.framework.constant;

import cn.boot.common.framework.exception.util.ErrorCode;

public interface SystemErrorCodeConstants {

    /**
     * OAUTH2 模块
     */
    ErrorCode OAUTH2_UNKNOWN = new ErrorCode(1001001000, "未知错误"); // 预留
    /**
     * 预留 1001001001 ~ 1001001099 错误码，方便前端
     */
    ErrorCode OAUTH2_ACCESS_TOKEN_NOT_FOUND = new ErrorCode(1001001001, "访问令牌不存在");
    ErrorCode OAUTH2_ACCESS_TOKEN_TOKEN_EXPIRED = new ErrorCode(1001001002, "访问令牌已过期");
    ErrorCode OAUTH2_REFRESH_TOKEN_NOT_FOUND = new ErrorCode(1001001005, "刷新令牌不存在");
    ErrorCode OAUTH_REFRESH_TOKEN_EXPIRED = new ErrorCode(1001001006, "访问令牌已过期");
    /**
     * 其它 1001001100 开始
     */
    ErrorCode OAUTH_USER_TYPE_ERROR = new ErrorCode(1001001101, "用户类型并不正确");


    /**
     * 服务降级 1001007000
     */
    ErrorCode SERVICE_DEGRADATION = new ErrorCode(1001007000, "服务器出错，请稍后重试");
    ErrorCode SERVICE_TO_BUSY = new ErrorCode(1001007001, "请求太快了，请稍后再试");


    // ========== 商品模块 1-003-000-000 ==========


}
