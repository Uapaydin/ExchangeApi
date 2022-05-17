package com.utku.exchange.data.dto.request;

import com.utku.exchange.util.validation.Currency;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author tcuapaydin
 * @created 17/05/2022 - 15:22
 */
@Data
public class ExchangeRequestDto {
    @NotNull
    private Double amount;
    @Currency(message = "Given currency is not valid")
    private String sourceCurrencyCode;
    @Currency(message = "Given currency is not valid")
    private String targetCurrencyCode;
}
