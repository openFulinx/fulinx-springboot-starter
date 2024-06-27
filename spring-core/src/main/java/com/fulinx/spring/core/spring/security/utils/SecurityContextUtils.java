/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.spring.security.utils;

import com.fulinx.spring.core.spring.security.model.AbstractUserModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SecurityContextUtils {

    public static SecurityContext getSecurityContext() {
        return SecurityContextHolder.getContext();
    }

    public static Optional<AbstractUserModel> getUserModel() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return Optional.empty();
        } else {
            Object object = authentication.getDetails();
            if (object instanceof AbstractUserModel) {
                return Optional.of((AbstractUserModel) object);
            } else {
                return Optional.empty();
            }
        }
    }

    public static Optional<?> getUserModel(Class<? extends AbstractUserModel> clazz) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return Optional.empty();
        } else {
            Object object = authentication.getDetails();
            return Optional.of(clazz.cast(object));
        }
    }
}
