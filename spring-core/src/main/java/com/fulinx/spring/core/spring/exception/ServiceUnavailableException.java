/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.spring.exception;

public class ServiceUnavailableException extends BusinessException {
    public ServiceUnavailableException(String message){
        super(message);
    }
    public ServiceUnavailableException(String message, Integer errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
