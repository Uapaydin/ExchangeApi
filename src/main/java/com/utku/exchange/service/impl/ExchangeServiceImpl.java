package com.utku.exchange.service.impl;

import com.utku.exchange.data.dto.integration.CurrencyRateDtoBase;
import com.utku.exchange.data.dto.request.ExchangeHistoryRequestDto;
import com.utku.exchange.data.dto.request.ExchangeRequestDto;
import com.utku.exchange.data.dto.response.ExchangeResultDto;
import com.utku.exchange.data.entity.ExchangeHistory;
import com.utku.exchange.data.repo.ExchangeHistoryRepository;
import com.utku.exchange.exception.CurrencyNotFoundException;
import com.utku.exchange.service.ApiLayerIntegration;
import com.utku.exchange.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Utku APAYDIN
 * @created 17/05/2022 - 13:09
 */
@RequiredArgsConstructor
@Service
public class ExchangeServiceImpl  implements ExchangeService {

    private final ExchangeHistoryRepository exchangeHistoryRepository;
    private final ApiLayerIntegration apiLayerIntegration;


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
    public ExchangeResultDto exchange(ExchangeRequestDto exchangeRateRequestDto) {
        String transactionId = UUID.randomUUID().toString();
        Double exchangeRate = getExchangeRate(exchangeRateRequestDto.getSourceCurrencyCode(),exchangeRateRequestDto.getTargetCurrencyCode());
        Double calculatedAmount =  exchangeRate * exchangeRateRequestDto.getAmount();
        saveExchangeTransaction(exchangeRateRequestDto, transactionId, exchangeRate);
        return new ExchangeResultDto(calculatedAmount,transactionId);
    }

    private void saveExchangeTransaction(ExchangeRequestDto exchangeRateRequestDto, String transactionId, Double exchangeRate) {
        ExchangeHistory exchangeHistory= new ExchangeHistory();
        exchangeHistory.setExchangeRate(exchangeRate);
        exchangeHistory.setAmount(exchangeRateRequestDto.getAmount());
        exchangeHistory.setSourceCurrency(exchangeRateRequestDto.getSourceCurrencyCode());
        exchangeHistory.setTargetCurrency(exchangeRateRequestDto.getTargetCurrencyCode());
        exchangeHistory.setTransactionId(transactionId);
        exchangeHistory.setRequestDate(Calendar.getInstance().getTime());
        exchangeHistoryRepository.save(exchangeHistory);
    }

    @Override
    public Map<String, String> getAvailableSymbols() {
        return apiLayerIntegration.getAvailableSymbols();
    }

    @Override
    public Page<ExchangeHistory> getExchangeHistory(ExchangeHistoryRequestDto exchangeHistoryRequestDto, int page, int size) {
        Pageable paging = PageRequest.of(page,size);
        Page<ExchangeHistory> foundPage = null;
        if(exchangeHistoryRequestDto.getTransactionDate() != null){
            foundPage = exchangeHistoryRepository.findByRequestDateAfter(exchangeHistoryRequestDto.getTransactionDate(),paging);
        }else if(exchangeHistoryRequestDto.getTransactionId()!=null && !exchangeHistoryRequestDto.getTransactionId().isEmpty()){
            foundPage = exchangeHistoryRepository.findByTransactionId(exchangeHistoryRequestDto.getTransactionId(),paging);
        }else{
            foundPage = exchangeHistoryRepository.findAll(paging);
        }
        return foundPage;
    }


}
