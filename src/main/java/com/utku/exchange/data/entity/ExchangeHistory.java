package com.utku.exchange.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Utku APAYDIN
 * @created 17/05/2022 - 11:45
 */
@Data
@Entity
@Table(name = "T_EXCHANGE_HISTORY")
@AllArgsConstructor
@NoArgsConstructor
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
