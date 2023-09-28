package com.tuling.tulingmall.common.exception;


import com.tuling.tulingmall.common.api.IErrorCode;

/**
 * 断言处理类，用于抛出各种API异常
 *2022/2/27.
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
