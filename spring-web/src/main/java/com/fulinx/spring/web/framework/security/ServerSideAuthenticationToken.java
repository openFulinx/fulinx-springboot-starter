/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.web.framework.security;


import com.fulinx.spring.core.spring.security.token.AbstractJwtAuthenticationToken;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Slf4j
public class ServerSideAuthenticationToken extends AbstractJwtAuthenticationToken {

    private static final long serialVersionUID = -7807752327184761340L;

    @Getter
    private ServerSideUserModel serverSideUserModel;

    public ServerSideAuthenticationToken(String accessToken, Collection<? extends GrantedAuthority> authorities, String userModel) {
        super(accessToken, authorities);
        this.setAuthenticated(true);
        this.setDetails(userModel);
    }

    public ServerSideAuthenticationToken(String accessToken, ServerSideUserModel serverSideUserModel) {
        this(accessToken, serverSideUserModel.getAuthorities(), serverSideUserModel.getUserType() == 1 ? "server" : "client");
        this.serverSideUserModel = serverSideUserModel;
    }
}
