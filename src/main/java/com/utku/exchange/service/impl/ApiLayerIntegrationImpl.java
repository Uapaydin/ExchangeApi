package com.utku.exchange.service.impl;

import com.utku.exchange.data.dto.integration.CurrencyRateDtoBase;
import com.utku.exchange.data.dto.integration.SymbolsBase;
import com.utku.exchange.properties.ApilayerParameters;
import com.utku.exchange.service.ApiLayerIntegration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Utku APAYDIN
 * @created 17/05/2022 - 13:27
 */

@Service
@Slf4j
public class ApiLayerIntegrationImpl implements ApiLayerIntegration {


    private final ApilayerParameters apilayerParameters;
    private final RestTemplate restTemplate;

    private Map<String,String> cachedAvailableSymbols;
    private ResponseEntity<?> responseEntity;

    public ApiLayerIntegrationImpl(ApilayerParameters apilayerParameters){
        restTemplate = new RestTemplate();
        cachedAvailableSymbols = new HashMap<>();
        this.apilayerParameters = apilayerParameters;
    }

    public Map<String, String>  getAvailableSymbols() {
        return cachedAvailableSymbols;
    }

    public CurrencyRateDtoBase getExchangeForCurrencyRates(String sourceCurrencyCode, String targetCurrencyCode) {
        responseEntity = restTemplate.exchange(String.format(apilayerParameters.getLatestUrl(),targetCurrencyCode,sourceCurrencyCode), HttpMethod.GET, createHeader(), CurrencyRateDtoBase.class);
        if(responseEntity.getBody() != null){
            return (CurrencyRateDtoBase) responseEntity.getBody();
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() {
        responseEntity = restTemplate.exchange(apilayerParameters.getSymbolsUrl(), HttpMethod.GET, createHeader(), SymbolsBase.class);
        Optional.ofNullable(responseEntity.getBody()).ifPresent(o -> cachedAvailableSymbols = ((SymbolsBase) o).getSymbols());
    }

    private HttpEntity<?> createHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", apilayerParameters.getKey());
        return new HttpEntity<>(headers);
    }
}
