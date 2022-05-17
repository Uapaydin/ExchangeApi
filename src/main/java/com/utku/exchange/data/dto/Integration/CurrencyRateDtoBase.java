package com.utku.exchange.data.dto.Integration;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @author tcuapaydin
 * @created 17/05/2022 - 13:24
 */
@Data
public class CurrencyRateDtoBase extends ApiLayerResponseDto{
    @JsonAlias("base")
    private String currencyName;
    private Date date;
    private  Map<String,Double> rates;
}
