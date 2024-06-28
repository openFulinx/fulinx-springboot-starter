/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.spring.security.jwt.factory;

import com.fulinx.spring.core.spring.security.jwt.emums.JwtTokenTypeEnum;
import com.fulinx.spring.core.spring.security.jwt.properties.JwtProperties;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class JwtFactory {

    private final JwtProperties jwtProperties;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

    public JwtFactory(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /**
     * 新建accessToken
     *
     * @param claims
     * @param currentDateTime
     * @return
     */
    public String createAccessToken(Map<String, Object> claims, LocalDateTime currentDateTime) {

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(jwtProperties.getIssuer())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(getAccessTokenExpiration(currentDateTime).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(createSigningKey(), signatureAlgorithm)
                .compact();
    }

    /**
     * 获取accessToken过期时间
     *
     * @param currentDateTime
     * @return
     */
    public LocalDateTime getAccessTokenExpiration(LocalDateTime currentDateTime) {
        return currentDateTime
                .plusSeconds(jwtProperties.getAccessTokenExpiration())
                .atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 新建refreshToken
     *
     * @param claims
     * @param currentDateTime
     * @return
     */
    public String createRefreshToken(Map<String, Object> claims, LocalDateTime currentDateTime) {

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(jwtProperties.getIssuer())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(getRefreshTokenExpiration(currentDateTime).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(createSigningKey(), signatureAlgorithm)
                .compact();
    }

    /**
     * 获取新建refreshToken过期时间
     *
     * @param currentDateTime
     * @return
     */
    public LocalDateTime getRefreshTokenExpiration(LocalDateTime currentDateTime) {
        return currentDateTime
                .plusSeconds(jwtProperties.getRefreshTokenExpiration())
                .atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * AES加密
     *
     * @return
     */
    public SecretKey createSigningKey() {
        return new SecretKeySpec(jwtProperties.getSigningKey().getBytes(), signatureAlgorithm.getJcaName());
    }

    /**
     * 是否是AccessToken
     *
     * @param claims
     * @return
     */
    public boolean isAccessToken(Claims claims) throws UnsupportedJwtException {
        JwtTokenTypeEnum jwtTokenTypeEnum = getTokenTypeEnum(claims);
        return JwtTokenTypeEnum.AccessToken == jwtTokenTypeEnum;
    }

    /**
     * 是否是RefreshToken
     *
     * @param claims
     * @return
     */
    public boolean isRefreshToken(Claims claims) throws UnsupportedJwtException {
        JwtTokenTypeEnum jwtTokenTypeEnum = getTokenTypeEnum(claims);
        return JwtTokenTypeEnum.RefreshToken == jwtTokenTypeEnum;
    }

    private JwtTokenTypeEnum getTokenTypeEnum(Claims claims) {
        Optional<JwtTokenTypeEnum> tokenTypeEnumOptional = JwtTokenTypeEnum.of((Integer) claims.get(JwtTokenTypeEnum.class.getName()));
        if (!tokenTypeEnumOptional.isPresent()) {
            throw new UnsupportedJwtException("JWT token type is missing.");
        }
        return tokenTypeEnumOptional.get();
    }

    public ClaimsBuilder getClaimsBuilder(String userDtoString, JwtTokenTypeEnum jwtTokenTypeEnum) {
        ClaimsBuilder claimsBuilder = Jwts.claims();
        claimsBuilder.add(Map.class.getName(), userDtoString);
        claimsBuilder.add(JwtTokenTypeEnum.class.getName(), jwtTokenTypeEnum.getIndex());
        return claimsBuilder;
    }
}
