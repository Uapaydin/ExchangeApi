package com.utku.exchange.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Utku APAYDIN
 * @created 17/05/2022 - 15:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRequestDto extends ExchangeRateRequestDto{
    @NotNull
    private BigDecimal amount;
}
