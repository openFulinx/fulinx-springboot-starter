/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.user.dto;

import com.fulinx.spring.data.mysql.dao.podo.user.UserListConditionPo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserQueryConditionDto extends UserListConditionPo {

    @Serial
    private static final long serialVersionUID = 2114874975307763750L;
}
