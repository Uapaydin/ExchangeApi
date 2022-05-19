package com.utku.exchange.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Utku APAYDIN
 * @created 17/05/2022 - 14:50
 */
public class BaseException extends RuntimeException {
    private final HttpStatus httpStatus;

    public BaseException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public BaseException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
