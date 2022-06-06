package com.utku.exchange.service;

import com.utku.exchange.data.dto.request.ExchangeRequestDto;
import com.utku.exchange.data.dto.request.ExchangeHistoryRequestDto;
import com.utku.exchange.data.dto.response.ExchangeResultDto;
import com.utku.exchange.data.entity.ExchangeHistory;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Utku APAYDIN
 * @created 17/05/2022 - 11:13
 */
public interface ExchangeService {
    BigDecimal getExchangeRate(String sourceCurrencyCode, String targetCurrencyCode );
    ExchangeResultDto exchange(ExchangeRequestDto exchangeRateRequestDto);
    Map<String,String> getAvailableSymbols();

    Page<ExchangeHistory> getExchangeHistory(ExchangeHistoryRequestDto exchangeHistoryRequestDto, int page, int size);
}
