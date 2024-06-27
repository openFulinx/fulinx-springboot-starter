/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.web.framework.security;

import com.fulinx.spring.web.framework.properties.JwtWebProperties;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Import({JwtWebProperties.class})
@Slf4j
public class AuthenticationRequestMatcher implements RequestMatcher {

    private final List<RequestMatcher> requestMatcherList;

    @Autowired
    public AuthenticationRequestMatcher(JwtWebProperties jwtWebProperties) {
        // 装载所有需要认证的入口点
        requestMatcherList = Stream.of(jwtWebProperties.getAuthenticationEndpoints())
                .map(AntPathRequestMatcher::new).collect(Collectors.toList());
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        for (RequestMatcher requestMatcher : requestMatcherList) {
            if (requestMatcher.matches(request)) {
                return true;
            }
        }
        return false;
    }
}
