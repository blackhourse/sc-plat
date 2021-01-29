package cn.boot.st.portal.constants;

import cn.boot.common.framework.exception.util.ErrorCode;

/*
 *  商品模块 1-003-000-000
 * */

/**
 * @author mht
 */
public interface TradeErrorCodeConstants {

    /**
     * 1005-000-000   ~ 1009-999-999
     */

    ErrorCode PARAM_PHONE_CODE_NULL = new ErrorCode(1005001001, "请输入验证码");
    ErrorCode REDIS_PHONE_CODE_EXPIRED = new ErrorCode(1005001002, "验证码已过期");
    ErrorCode REDIS_PHONE_CODE_ERROR = new ErrorCode(1005001003, "验证码不正确");

    ErrorCode MEMBER_NOT_EXIST = new ErrorCode(1005002001, "用户不存在");



}
