package com.utku.exchange.handler;

import com.utku.exchange.exception.BaseException;
import com.utku.exchange.exception.CurrencyNotFoundException;
import com.utku.exchange.exception.QueryExchangeHistoryException;
import com.utku.exchange.util.ResponseBuilder;
import com.utku.exchange.util.enumaration.ReturnType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
/**
 * @author Utku APAYDIN
 * @created 18/05/2022 - 19:05
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalRestControllerExceptionHandler extends BaseHandler {

    @ExceptionHandler(CurrencyNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(CurrencyNotFoundException e, HttpServletRequest request, HttpServletResponse response) {
        log.error("Given currency not found", e);
        return handleException(e, request, e.getHttpStatus());
    }
    @ExceptionHandler(QueryExchangeHistoryException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(QueryExchangeHistoryException e, HttpServletRequest request, HttpServletResponse response) {
        return handleException(e, request, e.getHttpStatus());
    }
    private ResponseEntity<Map<String, Object>> handleException(BaseException ex, HttpServletRequest request, HttpStatus status) {
        return new ResponseBuilder(status, ReturnType.FAILURE).withError(ex.getMessage()).build();
    }
}
