package com.utku.exchange.data.dto.response;

import com.utku.exchange.data.entity.ExchangeHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author APAYDIN
 * @created 17/05/2022 - 11:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeHistoryDto {
    private String sourceCurrency;
    private String targetCurrency;
    private String exchangeRate;
    private String amount;
    private Date requestDate;

    public ExchangeHistoryDto(ExchangeHistory source){
        BeanUtils.copyProperties(source,this);
    }
}
