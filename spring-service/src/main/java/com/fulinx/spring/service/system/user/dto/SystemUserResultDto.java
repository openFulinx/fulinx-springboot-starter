/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.system.user.dto;

import com.fulinx.spring.core.spring.security.model.AbstractUserModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
public class SystemUserResultDto extends AbstractUserModel {

    @Serial
    private static final long serialVersionUID = -2970554953499710816L;

    private Integer systemUserId;

    private Collection systemUserRoleIds;

    private Integer userType;

    private Integer userProfileId;

    private String username;

    private String firstName;

    private String lastName;

    private Integer gender;

    private String telephone;

    private String post;

    private String operatorName;

    private Collection<? extends GrantedAuthority> authorities;
}
