package com.utku.exchange.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author Utku APAYDIN
 * @created 17/05/2022 - 11:17
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeHistoryRequestDto {
    private String transactionId;
    private Date transactionDate;
}
