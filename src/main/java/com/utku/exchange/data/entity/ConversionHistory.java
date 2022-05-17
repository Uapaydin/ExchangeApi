package com.utku.exchange.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author tcuapaydin
 * @created 17/05/2022 - 11:45
 */
@Data
@Entity
@Table(name = "T_CONVERSATION_HISTORY")
public class ConversionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String transactionId;
    private String sourceCurrency;
    private String targetCurrency;
    private String exchangeRate;
    private String amount;
    private Date requestDate;
}
