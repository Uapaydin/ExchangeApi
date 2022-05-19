package com.utku.exchange.util.enumaration;
/**
 * @author Utku APAYDIN
 * @created 17/05/2022 - 17:31
 */
public enum ReturnType {
    SUCCESS(0,"The operation succeeded."),
    FAILURE(-1,"An error occured");

    int code;
    String message;

    ReturnType(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
