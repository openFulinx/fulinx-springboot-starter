/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.user.dto;

import com.fulinx.spring.data.mysql.dao.podo.user.UserListResultDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserQueryResultDto extends UserListResultDo {
    @Serial
    private static final long serialVersionUID = 3295178635580464415L;
}
