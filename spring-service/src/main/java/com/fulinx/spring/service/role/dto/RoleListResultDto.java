/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.role.dto;

import com.fulinx.spring.data.mysql.dao.podo.role.RoleListResultDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleListResultDto extends RoleListResultDo {
    @Serial
    private static final long serialVersionUID = -4486639296131025460L;
}
