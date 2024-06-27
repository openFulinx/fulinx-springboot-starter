/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.spring.security.token;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public abstract class AbstractJwtAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = -5299100596902119667L;

    @Getter
    protected final String accessToken;

    public AbstractJwtAuthenticationToken(String accessToken, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.accessToken = accessToken;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return accessToken;
    }
}
