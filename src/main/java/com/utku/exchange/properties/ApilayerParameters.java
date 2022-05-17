package com.utku.exchange.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author tcuapaydin
 * @created 17/05/2022 - 13:20
 */
@Data
@Component
@ConfigurationProperties(prefix = "apilayer")
public class ApilayerParameters {
    private String key;
    private String latestUrl;
    private String symbolsUrl;
}
