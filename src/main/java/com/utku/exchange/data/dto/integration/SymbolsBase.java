package com.utku.exchange.data.dto.integration;

import lombok.Data;

import java.util.Map;

/**
 * @author APAYDIN
 * @created 17/05/2022 - 13:59
 */
@Data
public class SymbolsBase extends ApiLayerResponseDto{
    private Map<String,String> symbols;
}


