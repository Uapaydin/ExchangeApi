package com.utku.exchange.util;

import com.utku.exchange.util.enumaration.ResponseDataKey;
import com.utku.exchange.util.enumaration.ReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseBuilder {
    private final HttpStatus resultStatus;
    private final Map<String, Object> result = new HashMap<>();

    public ResponseBuilder(final HttpStatus resultStatus, final ReturnType returnType) {
        this.resultStatus = resultStatus;
        result.put("returnCode", returnType.getCode());
        result.put("returnMessage", returnType.getMessage());
        result.put("isPaginated", false);
    }

    public ResponseBuilder withError(final Exception e) {
        result.put(ResponseDataKey.ERROR.getKey(),e);
        return this;
    }

    public ResponseBuilder withError(final String error) {
        result.put(ResponseDataKey.ERROR.getKey(),error);
        return this;
    }

    public ResponseBuilder withError(final Object o) {
        result.put(ResponseDataKey.ERROR.getKey(),o);
        return this;
    }

    public ResponseBuilder withData(final Object o) {
        result.put(ResponseDataKey.CONTENT.getKey(),o);
        return this;
    }

    public ResponseBuilder withMessage(final Object o) {
        result.put(ResponseDataKey.MESSAGE.getKey(),o);
        return this;
    }

    public ResponseBuilder withPaginatedData(final Object datalist) {
        result.put("isPaginated", true);
        return withData(datalist);
    }

    public ResponseEntity<Map<String, Object>> build(){
        return new ResponseEntity<>(result,resultStatus);
    }

}
