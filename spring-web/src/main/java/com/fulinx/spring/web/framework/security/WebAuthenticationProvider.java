/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.web.framework.security;


import com.fulinx.spring.core.spring.config.jackson.JacksonConfig;
import com.fulinx.spring.service.system.user.ISystemUserProfileService;
import com.fulinx.spring.service.system.user.ISystemUserService;
import com.fulinx.spring.service.user.IUserService;
import com.fulinx.spring.web.framework.properties.JwtWebProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Slf4j
@Configuration
@Import({JacksonConfig.class, JwtWebProperties.class})
public class WebAuthenticationProvider implements AuthenticationProvider {

    private final ISystemUserProfileService isystemUserProfileService;
    private final ISystemUserService iSystemUserService;
    private final IUserService iUserService;

    @Autowired
    @Lazy
    public WebAuthenticationProvider(ISystemUserProfileService isystemUserProfileService, ISystemUserService iSystemUserService, IUserService iUserService) {
        this.isystemUserProfileService = isystemUserProfileService;
        this.iSystemUserService = iSystemUserService;
        this.iUserService = iUserService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication authenticationResult = null;
        if (authentication instanceof ServerSideAuthenticationToken) {
            ServerSideAuthenticationToken serverSideAuthenticationToken = (ServerSideAuthenticationToken) authentication;
            ServerSideUserModel serverSideUserModel = serverSideAuthenticationToken.getServerSideUserModel();
            iSystemUserService.lockById(serverSideUserModel.getSystemUserId()).orElseThrow(() -> {
                log.error("User account information not found, userId={}", serverSideUserModel.getSystemUserId());
                return new AuthenticationServiceException("User account does not exist.");
            });
            authenticationResult = serverSideAuthenticationToken;
        } else {
            ClientSideAuthenticationToken clientSideAuthenticationToken = (ClientSideAuthenticationToken) authentication;
            ClientSideUserModel clientSideUserModel = clientSideAuthenticationToken.getClientSideUserModel();
            iUserService.lockById(clientSideUserModel.getUserId()).orElseThrow(() -> {
                log.error("User account information not found, userId={}", clientSideUserModel.getUserId());
                return new AuthenticationServiceException("User account does not exist.");
            });
            authenticationResult = clientSideAuthenticationToken;
        }
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ServerSideAuthenticationToken.class.isAssignableFrom(authentication) ||
                ClientSideAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

