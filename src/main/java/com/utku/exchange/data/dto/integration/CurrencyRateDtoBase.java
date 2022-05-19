package com.utku.exchange.data.dto.integration;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.Map;

/**
 * @author Utku APAYDIN
 * @created 17/05/2022 - 13:24
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRateDtoBase extends ApiLayerResponseDto{
    @JsonAlias("base")
    private String currencyName;
    private Date date;
    private  Map<String,Double> rates;
}
