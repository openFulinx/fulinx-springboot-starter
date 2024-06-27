/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.web.framework.config;

import com.fulinx.spring.core.spring.security.jwt.factory.JwtFactory;
import com.fulinx.spring.web.framework.properties.JwtWebProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class JwtWebConfig {
    private final JwtWebProperties jwtWebProperties;

    public JwtWebConfig(JwtWebProperties jwtWebProperties) {
        this.jwtWebProperties = jwtWebProperties;
    }

    @Bean
    public JwtFactory jwtFactory(){
        return new JwtFactory(this.jwtWebProperties);
    }
}
