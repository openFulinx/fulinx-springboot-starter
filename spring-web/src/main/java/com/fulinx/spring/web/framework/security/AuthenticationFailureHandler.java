/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.web.framework.security;


import com.fulinx.spring.core.spring.config.jackson.JacksonConfig;
import com.fulinx.spring.core.spring.security.handler.AbstractJwtAuthenticationFailureHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 登录失败处理器
 */
@Slf4j
@Component
@Import({JacksonConfig.class})
public class AuthenticationFailureHandler extends AbstractJwtAuthenticationFailureHandler {

    @Autowired
    public AuthenticationFailureHandler(JacksonConfig jacksonConfig) {
        super(jacksonConfig);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        super.onAuthenticationFailure(request, response, exception);
    }
}
