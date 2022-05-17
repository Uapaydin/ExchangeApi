package com.utku.exchange.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * @author APAYDIN
 * @created 17/05/2022 - 20:50
 */
@Data
@AllArgsConstructor
public class ExchangeResultDto {
    private Double calculatedAmount;
    private String transactionId;
}
