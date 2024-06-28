/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.podo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserListConditionPo implements Serializable {

    @Serial
    private static final long serialVersionUID = 2846304759613907925L;

    @Schema(description = "User Id")
    private Integer id;

    @Schema(description = "Telephone")
    private String telephone;

    @Schema(description = "First Name")
    private String firstName;

    @Schema(description = "Last Name")
    private String lastName;

    @Schema(description = "Status: 1 - Enabled, 9 - Disabled")
    private Integer status;

    @Schema(description = "Soft Delete Flag")
    private Integer isDelete;
}
