package com.apex.app.common.exception;

import com.apex.app.common.api.IErrorCode;

/**
 * Assertion handling class for throwing various API exceptions
 * @author Mingze Ma
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}