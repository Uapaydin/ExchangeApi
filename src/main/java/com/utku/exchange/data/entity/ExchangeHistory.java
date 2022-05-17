package com.utku.exchange.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author APAYDIN
 * @created 17/05/2022 - 11:45
 */
@Data
@Entity
@Table(name = "T_EXCHANGE_HISTORY")
public class ExchangeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String transactionId;
    private String sourceCurrency;
    private String targetCurrency;
    private Double exchangeRate;
    private Double amount;
    private Date requestDate;
}
