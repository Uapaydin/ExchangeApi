package com.utku.exchange.service;

import com.utku.exchange.data.dto.integration.CurrencyRateDtoBase;
import org.springframework.beans.factory.InitializingBean;

import java.util.Map;

/**
 * @author APAYDIN
 * @created 17/05/2022 - 13:21
 */
public interface ApiLayerIntegration extends InitializingBean {
    Map<String, String> getAvailableSymbols();
    CurrencyRateDtoBase getExchangeForCurrencyRates(String currencyCode, String targetCurrencyCode);
}
