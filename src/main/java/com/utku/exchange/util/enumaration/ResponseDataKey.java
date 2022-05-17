package com.utku.exchange.util.enumaration;

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

