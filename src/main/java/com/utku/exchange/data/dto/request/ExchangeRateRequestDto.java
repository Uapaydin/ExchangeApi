package com.utku.exchange.data.dto.request;

import com.utku.exchange.util.validation.Currency;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author tcuapaydin
 * @created 17/05/2022 - 11:16
 */
@Data
public class ExchangeRateRequestDto {
    @NotBlank
    @Currency(message = "Given currency is not valid")
    private String sourceCurrencyCode;
    @NotBlank
    @Currency(message = "Given currency is not valid")
    private String targetCurrencyCode;
}
