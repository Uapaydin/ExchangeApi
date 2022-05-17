package com.utku.exchange.data.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author APAYDIN
 * @created 17/05/2022 - 15:22
 */
@Data
public class ExchangeRequestDto extends ExchangeRateRequestDto{
    @NotNull
    private Double amount;
}
