package com.utku.exchange.handler;

import com.utku.exchange.util.ResponseBuilder;
import com.utku.exchange.util.enumaration.ReturnType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
/**
 * @author Utku APAYDIN
 * @created 18/05/2022 - 19:02
 */

public class BaseHandler {
    protected  static final Logger LOGGER = LoggerFactory.getLogger(BaseHandler.class);

    protected final ResponseEntity<Map<String, Object>> buildResponseEntity(Exception e, HttpStatus status){
        return new ResponseBuilder(status, ReturnType.FAILURE).withError(e.getMessage()).build();
    }
}
