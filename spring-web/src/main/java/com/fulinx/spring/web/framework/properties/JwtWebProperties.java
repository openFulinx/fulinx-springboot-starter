/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.web.framework.properties;


import com.fulinx.spring.core.spring.factory.YamlPropertySourceFactory;
import com.fulinx.spring.core.spring.security.jwt.properties.JwtProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@EqualsAndHashCode(callSuper = false)
@Configuration
@ConfigurationProperties(prefix = "jwt.web")
@PropertySource(factory = YamlPropertySourceFactory.class, value="classpath:jwt.yml")
public class JwtWebProperties extends JwtProperties {
    private static final long serialVersionUID = 8549933479145924256L;
}
