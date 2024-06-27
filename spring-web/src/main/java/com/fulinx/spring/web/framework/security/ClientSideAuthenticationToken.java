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
public class ClientSideAuthenticationToken extends AbstractJwtAuthenticationToken {

    private static final long serialVersionUID = -7807752327184761340L;

    @Getter
    private ClientSideUserModel clientSideUserModel;

    public ClientSideAuthenticationToken(String accessToken) {
        super(accessToken, null);
        this.setAuthenticated(true);
    }

    public ClientSideAuthenticationToken(String accessToken, Collection<? extends GrantedAuthority> authorities) {
        super(accessToken, authorities);
        this.setAuthenticated(true);
    }

    public ClientSideAuthenticationToken(String accessToken, ClientSideUserModel clientSideUserModel) {
        this(accessToken);
        this.clientSideUserModel = clientSideUserModel;
    }
}
