package com.utku.exchange.service.impl;

import com.utku.exchange.data.MockTestData;
import com.utku.exchange.data.dto.integration.CurrencyRateDtoBase;
import com.utku.exchange.data.dto.request.ExchangeHistoryRequestDto;
import com.utku.exchange.data.dto.request.ExchangeRequestDto;
import com.utku.exchange.data.dto.response.ExchangeResultDto;
import com.utku.exchange.data.entity.ExchangeHistory;
import com.utku.exchange.data.repo.ExchangeHistoryRepository;
import com.utku.exchange.service.ApiLayerIntegration;
import com.utku.exchange.service.ExchangeService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.hibernate5.HibernateTemplate;

import javax.validation.constraints.AssertTrue;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Utku APAYDIN
 * @created 18/05/2022 - 9:04 PM
 */
class ExchangeServiceImplTest {

//    private final ExchangeHistoryRepository exchangeHistoryRepository;
//    private final ApiLayerIntegration apiLayerIntegration;
    Random rnd = new Random();

    @Mock
    ApiLayerIntegrationImpl apiLayerIntegration;

    @Mock
    ExchangeHistoryRepository exchangeHistoryRepository;


    @InjectMocks
    ExchangeServiceImpl exchangeService;
    private final Double EXCHANGE_AMOUNT = 100.0;
    private final String WRONG_TRANSACTION_ID = "WRONG_TRANSACTION_ID";
    private final int DEFAULT_PAGE = 0;
    private final int DEFAULT_SIZE = 10;

    private HibernateTemplate hibernateTemplateMock;

    @BeforeEach
    void init() throws ParseException {
        MockitoAnnotations.openMocks(this);


        Mockito.when(apiLayerIntegration.getAvailableSymbols()).thenReturn(MockTestData.getSymbolListData());
        MockTestData.getRates().forEach((sourceSymbol,rates) -> {
            rates.forEach((targetSymbol,rate)->{
                Mockito.when(apiLayerIntegration.getExchangeForCurrencyRates(sourceSymbol,targetSymbol)).thenReturn(
                        CurrencyRateDtoBase.builder()
                                .currencyName(sourceSymbol)
                                .success(true)
                                .rates(Collections.singletonMap(targetSymbol,rate)).build());
            });
        });

        Mockito.when(exchangeHistoryRepository.findAll()).thenReturn(MockTestData.getExchangeHistoryData());
        MockTestData.getExchangeHistoryData().forEach(item ->
                Mockito.when(exchangeHistoryRepository.findByTransactionId(item.getTransactionId(),
                        PageRequest.of(DEFAULT_PAGE,DEFAULT_SIZE))).thenReturn(new PageImpl<>(List.of(item))));
        Mockito.when(exchangeHistoryRepository.findByTransactionId(WRONG_TRANSACTION_ID,PageRequest.of(DEFAULT_PAGE,DEFAULT_SIZE))).thenReturn(Page.empty());
        Mockito.when(exchangeHistoryRepository.findAll(PageRequest.of(DEFAULT_PAGE,DEFAULT_SIZE))).thenReturn(new PageImpl<>(MockTestData.getExchangeHistoryData()));
        MockTestData.getExchangeHistoryData().forEach(item ->{
            try {
                List<ExchangeHistory> filteredData = MockTestData.getExchangeHistoryData().stream().filter(record -> record.getRequestDate().after(item.getRequestDate())).collect(Collectors.toList());
                Mockito.when(exchangeHistoryRepository.findByRequestDateAfter(item.getRequestDate(),PageRequest.of(DEFAULT_PAGE,DEFAULT_SIZE))).thenReturn(new PageImpl<>(filteredData));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }


    @Test
    void testGetExchangeRate() {
        String randomSymbol1 = MockTestData.getSymbolListData().keySet().toArray()[rnd.nextInt(MockTestData.getSymbolListData().size())].toString();
        String randomSymbol2 = MockTestData.getSymbolListData().keySet().toArray()[rnd.nextInt(MockTestData.getSymbolListData().size())].toString();
        Double rate = exchangeService.getExchangeRate(randomSymbol1,randomSymbol2);
        Double actualRate = MockTestData.getRateForSymbol(randomSymbol1).get(randomSymbol2);
        assertThat(rate).isEqualTo(actualRate);
    }

    @Test
    void testExchange() {
        String randomSymbol1 = MockTestData.getSymbolListData().keySet().toArray()[rnd.nextInt(MockTestData.getSymbolListData().size())].toString();
        String randomSymbol2 = MockTestData.getSymbolListData().keySet().toArray()[rnd.nextInt(MockTestData.getSymbolListData().size())].toString();
        Double actualRate = MockTestData.getRateForSymbol(randomSymbol1).get(randomSymbol2);
        ExchangeRequestDto requestDto = new ExchangeRequestDto();
        requestDto.setSourceCurrencyCode(randomSymbol1);
        requestDto.setTargetCurrencyCode(randomSymbol2);
        requestDto.setAmount(EXCHANGE_AMOUNT);
        ExchangeResultDto resultDto = exchangeService.exchange(requestDto);
        assertThat(resultDto.getCalculatedAmount()).isEqualTo((actualRate * EXCHANGE_AMOUNT));
    }

    @Test
    void testGetAvailableSymbols() {
        String randomSymbol = MockTestData.getSymbolListData().keySet().toArray()[rnd.nextInt(MockTestData.getSymbolListData().size())].toString();
        Map<String,String> symbols = exchangeService.getAvailableSymbols();
        assertThat(symbols.get(randomSymbol)).isNotNull();
    }

    @Test
    void testGetExchangeHistoryWithTransactionId() throws ParseException {
        String randomTransactionId = MockTestData.getExchangeHistoryData().get(rnd.nextInt(MockTestData.getExchangeHistoryData().size())).getTransactionId();
        Page<ExchangeHistory> result = exchangeService.getExchangeHistory(ExchangeHistoryRequestDto.builder().transactionId(randomTransactionId).build(),DEFAULT_PAGE,DEFAULT_SIZE);
        assertThat(result.getSize()).isEqualTo(1);
    }

    @Test
    void testGetExchangeHistoryWithWrongTransactionId() throws ParseException {
        Page<ExchangeHistory> result = exchangeService.getExchangeHistory(ExchangeHistoryRequestDto.builder().transactionId("WRONG_TRANSACTION_ID").build(),DEFAULT_PAGE,DEFAULT_SIZE);
        assertThat(result.getSize()).isZero();
    }


    @Test
    void testGetExchangeHistoryWithDate() throws ParseException {
        Date randomDateForExchangeHistory = (Date) MockTestData.getExchangeHistoryDateCountMap().keySet().toArray()[rnd.nextInt(MockTestData.getExchangeHistoryData().size())];
        Page<ExchangeHistory> result = exchangeService.getExchangeHistory(ExchangeHistoryRequestDto.builder().transactionDate(randomDateForExchangeHistory).build(),DEFAULT_PAGE,DEFAULT_SIZE);
        assertThat(result.getSize()).isEqualTo(MockTestData.getExchangeHistoryDateCountMap().get(randomDateForExchangeHistory));
    }

    @Test
    void testGetExchangeHistoryWithNullDate() throws ParseException {
        Page<ExchangeHistory> result = exchangeService.getExchangeHistory(ExchangeHistoryRequestDto.builder().transactionDate(null).build(),DEFAULT_PAGE,DEFAULT_SIZE);
        assertThat(result.getSize()).isEqualTo(MockTestData.getExchangeHistoryData().size());
    }

}