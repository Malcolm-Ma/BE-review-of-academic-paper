package com.apex.app.common.api;

/**
 * @author Mingze Ma
 */
public enum StatusCode implements ErrorCodeInterface {
    SUCCESS(200, "Success"),
    FAILED(500, "Failure"),
    VALIDATE_FAILED(404, "Parameter check failed"),
    UNAUTHORIZED(401, "Not logged in or token has expired"),
    FORBIDDEN(403, "NO Permission");

    private long code;
    private String message;

    private StatusCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
