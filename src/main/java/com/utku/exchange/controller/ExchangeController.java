package com.utku.exchange.controller;

import com.utku.exchange.data.dto.request.ExchangeRateRequestDto;
import com.utku.exchange.data.dto.request.ExchangeRequestDto;
import com.utku.exchange.service.ApiLayerIntegration;
import com.utku.exchange.service.ExchangeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author tcuapaydin
 * @created 17/05/2022 - 12:02
 */
@RestController
public class ExchangeController {

    private final ExchangeService exchangeService;


    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("api/rate")
    public Double getRate(@Valid @RequestBody ExchangeRateRequestDto  exchangeRateRequestDto) {
        return exchangeService.getExchangeRate(exchangeRateRequestDto.getSourceCurrencyCode(), exchangeRateRequestDto.getTargetCurrencyCode());
    }

    @GetMapping("api/exchange")
    public Double exchange(@Valid @RequestBody ExchangeRequestDto exchangeRateRequestDto) {
        return exchangeService.exchange(exchangeRateRequestDto);
    }

    @GetMapping("api/symbols")
    public Map<String,String> getCachedSymbols(){
        return exchangeService.getAvailableSymbols();
    }

}
