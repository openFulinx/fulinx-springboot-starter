package com.fulinx.spring.web.framework.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fulinx.spring.core.spring.config.jackson.JacksonConfig;
import com.fulinx.spring.core.spring.security.jwt.factory.JwtFactory;
import com.fulinx.spring.web.framework.properties.JwtWebProperties;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.Key;
import java.util.Map;

@Slf4j
@Component
@Import({JwtWebProperties.class})
public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final JwtWebProperties jwtWebProperties;
    private final JwtFactory jwtFactory;
    private final JacksonConfig jacksonConfig;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    public AuthenticationFilter(JwtWebProperties jwtWebProperties, RequestMatcher authenticationRequestMatcher, JwtFactory jwtFactory, JacksonConfig jacksonConfig, AuthenticationSuccessHandler authenticationSuccessHandler, AuthenticationFailureHandler authenticationFailureHandler) {
        super(authenticationRequestMatcher);
        this.jwtWebProperties = jwtWebProperties;
        this.jwtFactory = jwtFactory;
        this.jacksonConfig = jacksonConfig;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String requestUrl = request.getRequestURI();
        String authHeader = request.getHeader(jwtWebProperties.getHeader());

        if (StringUtils.isEmpty(authHeader)) {
            log.warn("Authorization header cannot be empty");
            throw new AuthenticationServiceException("Bad request.");
        } else if (authHeader.length() < jwtWebProperties.getTokenPrefix().length()) {
            log.warn("Invalid authorization header size");
            throw new AuthenticationServiceException("Bad request.");
        }

        String accessToken = authHeader.substring(jwtWebProperties.getTokenPrefix().length());
        Key signingKey = jwtFactory.createSigningKey();

        try {
            JwtParser jwtParser = Jwts.parser().setSigningKey(signingKey).build();
            Jws<Claims> claimsJws = jwtParser.parseClaimsJws(accessToken);
            Claims claims = claimsJws.getBody();

            if (!jwtFactory.isAccessToken(claims)) {
                throw new UnsupportedJwtException("JWT Merchant token type is not match.");
            }

            String jsonString = claims.get(Map.class.getName(), String.class);
            Authentication authRequest = null;

            if (requestUrl.startsWith("/api/server-side/")) {
                authRequest = createServerSideAuthToken(accessToken, jsonString);
            }
            return performAuthentication(authRequest);
        } catch (JwtException | IllegalArgumentException ex) {
            log.error("Invalid JWT token: ", ex);
            throw new BadCredentialsException("Invalid JWT token: ", ex);
        } catch (JsonProcessingException ex) {
            log.error("JSON processing failed: ", ex);
            throw new AuthenticationServiceException("JSON processing failed: ", ex);
        }
    }

    private Authentication createServerSideAuthToken(String accessToken, String jsonString) throws JsonProcessingException {
        ServerSideUserModel serverSideUserModel = jacksonConfig.objectMapper().readValue(jsonString, ServerSideUserModel.class);
        if (serverSideUserModel == null) {
            log.error("Invalid access token, because serverSideUserModel is null, claims string is = {}", jsonString);
            throw new UnsupportedJwtException("JWT Merchant token is invalid.");
        }
        return new ServerSideAuthenticationToken(accessToken, serverSideUserModel);
    }

    private Authentication performAuthentication(Authentication authRequest) {
        try {
            Authentication authentication = getAuthenticationManager().authenticate(authRequest);
            return authentication;
        } catch (AuthenticationException e) {
            log.error("Authentication failed", e);
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error during authentication", e);
            throw new AuthenticationServiceException("Unexpected error during authentication", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        authenticationSuccessHandler.onAuthenticationSuccess(request, response, authResult);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        authenticationFailureHandler.onAuthenticationFailure(request, response, failed);
    }

    @Override
    public void afterPropertiesSet() {
        // 防止Autowired时，因authenticationManager没有设置时的异常
    }

}
