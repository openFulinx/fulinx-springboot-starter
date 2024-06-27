/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.spring.security.handler;

import com.fulinx.spring.core.generic.ResultVo;
import com.fulinx.spring.core.spring.config.jackson.JacksonConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

@Slf4j
public abstract class AbstractJwtAuthenticationFailureHandler implements AuthenticationFailureHandler {

    protected final JacksonConfig jacksonConfig;

    public AbstractJwtAuthenticationFailureHandler(JacksonConfig jacksonConfig) {
        this.jacksonConfig = jacksonConfig;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        Throwable throwable = exception.getCause();
        String errorMessage;
        if (throwable != null) {
            errorMessage = throwable.getMessage();
        } else {
            errorMessage = exception.getMessage();
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        jacksonConfig.objectMapper().writeValue(response.getWriter(), ResultVo.build(errorMessage, -9));
    }
}
