package com.utku.exchange.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Utku APAYDIN
 * @created 19/05/2022 - 12:34 PM
 */
public class QueryExchangeHistoryException  extends BaseException {

    private static final HttpStatus HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final String MESSAGE = "You can not query with both transactionId and transactionDate.";

    public QueryExchangeHistoryException() {
        super(HTTP_STATUS, MESSAGE);
    }
}
