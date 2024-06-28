/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.podo.user;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDetailDo implements Serializable {

    private static final long serialVersionUID = 3670541294069365380L;

    @Parameter(name = "User Id")
    private Integer userId;

    @Schema(description = "First Name")
    private String firstName;

    @Schema(description = "Last Name")
    private String lastName;
}
