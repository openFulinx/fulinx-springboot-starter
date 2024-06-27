/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.system.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Data
@EqualsAndHashCode
public class SystemUserAuthDto {

    private Integer SystemUserId;

    private String userName;

    private Collection systemUserRoleIds;

    private String operatorName;

}
