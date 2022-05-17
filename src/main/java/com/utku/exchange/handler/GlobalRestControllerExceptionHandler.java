package com.utku.exchange.handler;

import com.utku.exchange.exception.BaseException;
import com.utku.exchange.exception.CurrencyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalRestControllerExceptionHandler extends BaseHandler {
    protected static final Logger LOGGER = LoggerFactory.getLogger(GlobalRestControllerExceptionHandler.class);

    @ExceptionHandler(CurrencyNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(CurrencyNotFoundException e, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.error("Given currency not found", e);
        return handleException(e, request, e.getHttpStatus());
    }

    private ResponseEntity<Map<String, Object>> handleException(BaseException ex, HttpServletRequest request, HttpStatus status) {

        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        body.put("status", status.value());
        body.put("uri", request.getRequestURI());

        return new ResponseEntity<>(body, status);
    }
}
