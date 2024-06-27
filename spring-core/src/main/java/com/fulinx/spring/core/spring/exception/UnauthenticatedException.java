/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.spring.exception;

public class UnauthenticatedException extends BusinessException {
    public UnauthenticatedException(String message) {
        super(message);
    }
    public UnauthenticatedException(String message, Integer errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
