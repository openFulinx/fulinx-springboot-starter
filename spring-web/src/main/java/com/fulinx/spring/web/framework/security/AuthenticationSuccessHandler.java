/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.web.framework.security;

import com.fulinx.spring.core.spring.security.handler.AbstractJwtAuthenticationSuccessHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 登录成功处理器
 */
@Slf4j
@Component
public class AuthenticationSuccessHandler extends AbstractJwtAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
