package com.utku.exchange.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Utku APAYDIN
 * @created 17/05/2022 - 20:50
 */
@Data
@Builder
@AllArgsConstructor
public class ExchangeResultDto {
    private BigDecimal calculatedAmount;
    private String transactionId;
}
