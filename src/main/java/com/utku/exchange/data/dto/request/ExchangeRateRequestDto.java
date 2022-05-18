package com.utku.exchange.data.dto.request;

import com.utku.exchange.util.validation.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

/**
 * @author APAYDIN
 * @created 17/05/2022 - 11:16
 */
@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class ExchangeRateRequestDto {
    @NotBlank
    @Currency(message = "Given currency is not valid")
    private String sourceCurrencyCode;
    @NotBlank
    @Currency(message = "Given currency is not valid")
    private String targetCurrencyCode;
}
