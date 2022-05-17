package com.utku.exchange.service.impl;

import com.utku.exchange.data.dto.integration.CurrencyRateDtoBase;
import com.utku.exchange.data.dto.request.ConversionHistoryRequestDto;
import com.utku.exchange.data.dto.request.ExchangeRequestDto;
import com.utku.exchange.data.dto.response.ConversionHistoryDto;
import com.utku.exchange.exception.CurrencyNotFoundException;
import com.utku.exchange.service.ApiLayerIntegration;
import com.utku.exchange.service.ExchangeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author APAYDIN
 * @created 17/05/2022 - 13:09
 */
@Service
public class ExchangeServiceImpl  implements ExchangeService {

    private final ApiLayerIntegration apiLayerIntegration;

    public ExchangeServiceImpl(ApiLayerIntegrationImpl apiLayerIntegration) {
        this.apiLayerIntegration = apiLayerIntegration;
    }

    @Override
    public Double getExchangeRate(String sourceCurrencyCode,String targetCurrencyCode ) {
        CurrencyRateDtoBase result = apiLayerIntegration.getExchangeForCurrencyRates(sourceCurrencyCode,targetCurrencyCode);
        if(result.isSuccess()){
            if(result.getRates().size()>0){
                return result.getRates().get(targetCurrencyCode);
            }else{
                throw new CurrencyNotFoundException(targetCurrencyCode);
            }
        }else{
            throw new CurrencyNotFoundException();
        }
    }

    @Override
    public Double exchange(ExchangeRequestDto exchangeRateRequestDto) {
        Double exchangeRate = getExchangeRate(exchangeRateRequestDto.getSourceCurrencyCode(),exchangeRateRequestDto.getTargetCurrencyCode());
        return exchangeRate * exchangeRateRequestDto.getAmount();
    }

    @Override
    public Map<String, String> getAvailableSymbols() {
        return apiLayerIntegration.getAvailableSymbols();
    }


    @Override
    public List<ConversionHistoryDto> getConversationHistory(ConversionHistoryRequestDto conversionHistoryRequestDto) {
        return null;
    }
}
