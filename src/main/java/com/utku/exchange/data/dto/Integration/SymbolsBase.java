package com.utku.exchange.data.dto.Integration;

import lombok.Data;

import java.util.Map;

/**
 * @author tcuapaydin
 * @created 17/05/2022 - 13:59
 */
@Data
public class SymbolsBase extends ApiLayerResponseDto{
    private Map<String,String> symbols;
}


