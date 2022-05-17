package com.utku.exchange.data.repo;

import com.utku.exchange.data.entity.ConversionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author APAYDIN
 * @created 17/05/2022 - 11:57
 */
public interface ConversionHistoryRepository extends JpaRepository<ConversionHistory, String> {
}
