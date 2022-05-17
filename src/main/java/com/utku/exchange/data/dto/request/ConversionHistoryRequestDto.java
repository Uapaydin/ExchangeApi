package com.utku.exchange.data.dto.request;

import lombok.Data;

import java.util.Date;

/**
 * @author tcuapaydin
 * @created 17/05/2022 - 11:17
 */
@Data
public class ConversionHistoryRequestDto {

    private String transactionId;
    private Date transactionDate;
}
