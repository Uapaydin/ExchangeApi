package com.utku.exchange.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utku.exchange.data.MockTestData;
import com.utku.exchange.data.dto.request.ExchangeHistoryRequestDto;
import com.utku.exchange.data.dto.request.ExchangeRateRequestDto;
import com.utku.exchange.data.dto.request.ExchangeRequestDto;
import com.utku.exchange.data.dto.response.ExchangeResultDto;
import com.utku.exchange.data.entity.ExchangeHistory;
import com.utku.exchange.service.ExchangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Utku APAYDIN
 * @created 18/05/2022 - 19:15
 */

@WebMvcTest(ExchangeController.class)
class ExchangeControllerTest {

    Random rnd = new Random();
    private final Double EXCHANGE_AMOUNT = 100.0;
    private final int DEFAULT_PAGE = 0;
    private final int DEFAULT_SIZE = 10;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ExchangeService exchangeService;

    @BeforeEach
    public void init() throws ParseException {
        Mockito.when(exchangeService.getAvailableSymbols()).thenReturn(MockTestData.getSymbolListData());
        MockTestData.getExchangeHistoryData().forEach(item ->{
            Page<ExchangeHistory> foundPage = new PageImpl<>(List.of(item));
            Mockito.when(exchangeService.getExchangeHistory(
                    ExchangeHistoryRequestDto.builder()
                            .transactionId(item.getTransactionId()).build(),
                            DEFAULT_PAGE,
                            DEFAULT_SIZE))
                    .thenReturn(foundPage);
        });
        MockTestData.getRates().forEach((sourceSymbol, rates) ->
                rates.forEach((targetSymbol, targetRate) ->{
                            Mockito.when(exchangeService.getExchangeRate(sourceSymbol, targetSymbol)).thenReturn(targetRate);
                            Mockito.when(exchangeService.exchange(ExchangeRequestDto.builder()
                                            .sourceCurrencyCode(sourceSymbol)
                                            .targetCurrencyCode(targetSymbol)
                                            .amount(EXCHANGE_AMOUNT).build()))
                                    .thenReturn(ExchangeResultDto.builder()
                                            .calculatedAmount(targetRate * EXCHANGE_AMOUNT)
                                            .transactionId("").build());
                        }
                )
        );
    }

    @Test
    void testGetRate() throws Exception{
        String randomSymbol1 = MockTestData.getSymbolListData().keySet().toArray()[rnd.nextInt(MockTestData.getSymbolListData().size())].toString();
        String randomSymbol2 = MockTestData.getSymbolListData().keySet().toArray()[rnd.nextInt(MockTestData.getSymbolListData().size())].toString();
        Double actualRate = MockTestData.getRateForSymbol(randomSymbol1).get(randomSymbol2);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/rate")
                .content(this.objectMapper.writeValueAsBytes(ExchangeRateRequestDto.builder()
                        .sourceCurrencyCode(randomSymbol1)
                        .targetCurrencyCode(randomSymbol2).build()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.content", is(actualRate)));
    }

    @Test
    void testGetRateWithWrongSourceSymbol() throws Exception{
        String randomSymbol2 = MockTestData.getSymbolListData().keySet().toArray()[rnd.nextInt(MockTestData.getSymbolListData().size())].toString();
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/rate")
                        .content(this.objectMapper.writeValueAsBytes(ExchangeRateRequestDto.builder()
                                .sourceCurrencyCode("WRONG_SYMBOL")
                                .targetCurrencyCode(randomSymbol2).build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.error", notNullValue()))
                .andExpect(jsonPath("$.returnCode", is(-1)));
    }

    @Test
    void testGetRateWithNullSourceSymbol() throws Exception{
        String randomSymbol2 = MockTestData.getSymbolListData().keySet().toArray()[rnd.nextInt(MockTestData.getSymbolListData().size())].toString();
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/rate")
                        .content(this.objectMapper.writeValueAsBytes(ExchangeRateRequestDto.builder()
                                .sourceCurrencyCode(null)
                                .targetCurrencyCode(randomSymbol2).build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.error", notNullValue()))
                .andExpect(jsonPath("$.returnCode", is(-1)));
    }

    @Test
    void testGetRateWithWrongTargetSymbol() throws Exception{
        String randomSymbol1 = MockTestData.getSymbolListData().keySet().toArray()[rnd.nextInt(MockTestData.getSymbolListData().size())].toString();
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/rate")
                        .content(this.objectMapper.writeValueAsBytes(ExchangeRateRequestDto.builder()
                                .sourceCurrencyCode(randomSymbol1)
                                .targetCurrencyCode("WRONG_SYMBOL").build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.error", notNullValue()))
                .andExpect(jsonPath("$.returnCode", is(-1)));
    }

    @Test
    void testGetRateWithNullTargetSymbol() throws Exception{
        String randomSymbol1 = MockTestData.getSymbolListData().keySet().toArray()[rnd.nextInt(MockTestData.getSymbolListData().size())].toString();

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/rate")
                        .content(this.objectMapper.writeValueAsBytes(ExchangeRateRequestDto.builder()
                                .sourceCurrencyCode(randomSymbol1)
                                .targetCurrencyCode(null).build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.error", notNullValue()))
                .andExpect(jsonPath("$.returnCode", is(-1)));
    }
    @Test
    void testExchange() throws Exception{
        String randomSymbol1 = MockTestData.getSymbolListData().keySet().toArray()[rnd.nextInt(MockTestData.getSymbolListData().size())].toString();
        String randomSymbol2 = MockTestData.getSymbolListData().keySet().toArray()[rnd.nextInt(MockTestData.getSymbolListData().size())].toString();
        Double actualRate = MockTestData.getRateForSymbol(randomSymbol1).get(randomSymbol2);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/exchange")
                        .content(this.objectMapper.writeValueAsBytes(ExchangeRequestDto.builder()
                                .sourceCurrencyCode(randomSymbol1)
                                .targetCurrencyCode(randomSymbol2)
                                .amount(EXCHANGE_AMOUNT).build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.content.calculatedAmount", is(actualRate * EXCHANGE_AMOUNT)));
    }
    @Test
    void testGetCachedSymbols() throws Exception{
        String randomSymbol = MockTestData.getSymbolListData().keySet().toArray()[rnd.nextInt(MockTestData.getSymbolListData().size())].toString();

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/symbols")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.content."+randomSymbol, notNullValue()));
    }

    @Test
    void testGetExchangeHistory() throws Exception{
        String randomTransactionId = MockTestData.getExchangeHistoryData().stream()
                .map(ExchangeHistory::getTransactionId)
                .collect(Collectors.toList()).get(rnd.nextInt(MockTestData.getExchangeHistoryData().size()));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/exchange/history")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsBytes(ExchangeHistoryRequestDto.builder()
                                .transactionId(randomTransactionId).build())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.content", notNullValue()));
    }

    @Test
    void testGetExchangeHistoryWithWrongTransactionId() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/exchange/history")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsBytes(ExchangeHistoryRequestDto.builder()
                                .transactionId("").build())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalItems", is(0)))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.content", notNullValue()));
    }

    @Test
    void testGetExchangeHistoryWithTransactionDate() throws Exception{
        Date randomDateForExchangeHistory = (Date) MockTestData.getExchangeHistoryDateCountMap().keySet().toArray()[rnd.nextInt(MockTestData.getExchangeHistoryData().size())];
        int resultSize = MockTestData.getExchangeHistoryDateCountMap().get(randomDateForExchangeHistory);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/exchange/history")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsBytes(ExchangeHistoryRequestDto.builder()
                                .transactionDate(randomDateForExchangeHistory).build())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.totalItems", is(resultSize)));
    }

    @Test
    void testGetExchangeHistoryWithTransactionDateAndTransactionId() throws Exception{
        String randomTransactionId = MockTestData.getExchangeHistoryData().stream()
                .map(ExchangeHistory::getTransactionId)
                .collect(Collectors.toList()).get(rnd.nextInt(MockTestData.getExchangeHistoryData().size()));
        Date randomDateForExchangeHistory = (Date) MockTestData.getExchangeHistoryDateCountMap().keySet().toArray()[rnd.nextInt(MockTestData.getExchangeHistoryData().size())];
        int resultSize = MockTestData.getExchangeHistoryDateCountMap().get(randomDateForExchangeHistory);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/exchange/history")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsBytes(ExchangeHistoryRequestDto.builder()
                                .transactionId(randomTransactionId)
                                .transactionDate(randomDateForExchangeHistory).build())))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.returnCode", is(-1)));
    }

}
