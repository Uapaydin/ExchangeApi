package com.utku.exchange.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Utku APAYDIN
 * @created 17/05/2022 - 14:49
 */
public class CurrencyNotFoundException extends BaseException {

    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;
    private static final String MESSAGE = "Given currency information is not valid.";

    public CurrencyNotFoundException(String targetCurrencyCode) {
        super(HTTP_STATUS, "Target currency information ( "+targetCurrencyCode+" ) is not found.");
    }

    public CurrencyNotFoundException() {
        super(HTTP_STATUS, MESSAGE);
    }
}
