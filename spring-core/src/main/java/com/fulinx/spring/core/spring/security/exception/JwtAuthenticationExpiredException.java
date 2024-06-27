/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.spring.security.exception;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationExpiredException extends AuthenticationException {

    @Getter
    private String token;

    public JwtAuthenticationExpiredException(String msg) {
        super(msg);
    }

    public JwtAuthenticationExpiredException(String token, String msg, Throwable t){
        super(msg, t);
        this.token = token;
    }
}
