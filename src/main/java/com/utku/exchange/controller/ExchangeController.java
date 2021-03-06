package com.utku.exchange.controller;

import com.utku.exchange.data.dto.request.ExchangeHistoryRequestDto;
import com.utku.exchange.data.dto.request.ExchangeRateRequestDto;
import com.utku.exchange.data.dto.request.ExchangeRequestDto;
import com.utku.exchange.data.dto.response.ExchangeHistoryDto;
import com.utku.exchange.exception.QueryExchangeHistoryException;
import com.utku.exchange.service.ExchangeService;
import com.utku.exchange.util.ResponseBuilder;
import com.utku.exchange.util.enumaration.ReturnType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author Utku APAYDIN
 * @created 17/05/2022 - 12:02
 */
@RestController
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;
    private ResponseBuilder responseBuilder;

    @GetMapping("api/rate")
    public ResponseEntity<Map<String, Object>> getRate(@Valid @RequestBody ExchangeRateRequestDto  exchangeRateRequestDto) {
        responseBuilder = new ResponseBuilder(HttpStatus.OK, ReturnType.SUCCESS);
        return responseBuilder.withData(exchangeService.getExchangeRate(exchangeRateRequestDto.getSourceCurrencyCode(), exchangeRateRequestDto.getTargetCurrencyCode())).build();
    }

    @GetMapping("api/exchange")
    public ResponseEntity<Map<String, Object>> exchange(@Valid @RequestBody ExchangeRequestDto exchangeRateRequestDto) {
        responseBuilder = new ResponseBuilder(HttpStatus.OK, ReturnType.SUCCESS);
        return responseBuilder.withData(exchangeService.exchange(exchangeRateRequestDto)).build();
    }

    @GetMapping("api/symbols")
    public ResponseEntity<Map<String, Object>>  getCachedSymbols(){
        responseBuilder = new ResponseBuilder(HttpStatus.OK, ReturnType.SUCCESS);
        return responseBuilder.withData(exchangeService.getAvailableSymbols()).build();
    }

    @GetMapping("api/exchange/history")
    public ResponseEntity<Map<String,Object>> getExchangeHistory(
            @Valid @RequestBody ExchangeHistoryRequestDto exchangeHistoryRequestDto,
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) throws Exception {
        if(exchangeHistoryRequestDto.getTransactionId() != null && exchangeHistoryRequestDto.getTransactionDate() != null){
            throw new QueryExchangeHistoryException();
        }
        responseBuilder = new ResponseBuilder(HttpStatus.OK, ReturnType.SUCCESS);
        return responseBuilder.withPaginatedData(exchangeService.getExchangeHistory(exchangeHistoryRequestDto,page,size), ExchangeHistoryDto.class).build();
    }


}
