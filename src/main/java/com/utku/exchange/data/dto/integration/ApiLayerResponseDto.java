package com.utku.exchange.data.dto.integration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Utku APAYDIN
 * @created 17/05/2022 - 13:58
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract  class ApiLayerResponseDto {
    private boolean success;
}
