/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.spring.exception;

import lombok.Getter;

public class BusinessException extends Exception {
    private static final long serialVersionUID = 644248083357843417L;
    @Getter
    protected Integer errorCode = -1;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
