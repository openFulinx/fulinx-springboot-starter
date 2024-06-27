/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.spring.security.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractUserModel implements Serializable {

    @Serial
    private static final long serialVersionUID = -3116924467960234361L;

    private String operatorName;
}
