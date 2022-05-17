package com.utku.exchange.data.dto.response;

import lombok.Data;

import java.util.Date;

/**
 * @author APAYDIN
 * @created 17/05/2022 - 11:16
 */
@Data
public class ConversionHistoryDto {
    private String sourceCurrency;
    private String targetCurrency;
    private String exchangeRate;
    private String amount;
    private Date requestDate;
}
