package com.mht.common.api;


import com.mht.common.exception.ApiException;

import java.util.Objects;

/**
 * 断言处理类，用于抛出各种API异常
 * Created by macro on 2020/2/27.
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }

    public static void isNull(Object object, String message) {
        if (Objects.isNull(object)) {
            throw new ApiException(message);
        }
    }

    public static void isTrue(Object object, String message) {
        if (Objects.isNull(object)) {
            throw new ApiException(message);
        }
    }


}
