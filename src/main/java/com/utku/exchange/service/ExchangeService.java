package com.utku.exchange.service;

import com.utku.exchange.data.dto.request.ExchangeRequestDto;
import com.utku.exchange.data.dto.response.ConversionHistoryDto;
import com.utku.exchange.data.dto.request.ConversionHistoryRequestDto;
import com.utku.exchange.data.dto.request.ExchangeRateRequestDto;

import java.util.List;
import java.util.Map;

/**
 * @author tcuapaydin
 * @created 17/05/2022 - 11:13
 */
public interface ExchangeService {
    Double getExchangeRate(String sourceCurrencyCode,String targetCurrencyCode );
    Double exchange(ExchangeRequestDto exchangeRateRequestDto);
    Map<String,String> getAvailableSymbols();
    List<ConversionHistoryDto> getConversationHistory(ConversionHistoryRequestDto conversionHistoryRequestDto);
}
