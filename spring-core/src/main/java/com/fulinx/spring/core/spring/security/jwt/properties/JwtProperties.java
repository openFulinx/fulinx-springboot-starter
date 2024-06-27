/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.spring.security.jwt.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class JwtProperties implements Serializable {
    private static final long serialVersionUID = 1293795285352282021L;
    private String issuer;
    private String header;
    private String signingKey;
    private String tokenPrefix;
    private Long accessTokenExpiration;
    private Long refreshTokenExpiration;
    private String[] authenticationEndpoints;
}
