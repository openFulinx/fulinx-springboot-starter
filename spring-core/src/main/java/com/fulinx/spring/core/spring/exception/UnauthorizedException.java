/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.spring.exception;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException(String message) {
        super(message);
    }
    public UnauthorizedException(String message, Integer errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
