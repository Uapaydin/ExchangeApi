package com.utku.exchange.util.enumaration;
/**
 * @author Utku APAYDIN
 * @created 17/05/2022 - 17:30
 */
public enum ResponseDataKey {
    CONTENT("content"), MESSAGE("message"), ERROR("error");
    private String key;
    ResponseDataKey(String key) {
        this.key = key;
    }
    public String getKey() {
        return key;
    }
}

