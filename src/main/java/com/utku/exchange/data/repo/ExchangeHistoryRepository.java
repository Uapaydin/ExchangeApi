package com.utku.exchange.data.repo;

import com.utku.exchange.data.entity.ExchangeHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * @author APAYDIN
 * @created 17/05/2022 - 11:57
 */
public interface ExchangeHistoryRepository extends JpaRepository<ExchangeHistory, String> {

    Page<ExchangeHistory> findByTransactionId(String transactionId, Pageable pageable);
    Page<ExchangeHistory> findByRequestDateAfter(Date date, Pageable pageable);
}
