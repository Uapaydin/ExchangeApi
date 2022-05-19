package com.utku.exchange.exception;

import org.springframework.http.HttpStatus;
/**
 * @author Utku APAYDIN
 * @created 17/05/2022 - 19:32
 */
public class DtoMappingForPaginationException extends BaseException {

    private static final HttpStatus HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final String MESSAGE = "Selected object type for pagination result is not correct.";

    public DtoMappingForPaginationException() {
        super(HTTP_STATUS, MESSAGE);
    }
}
