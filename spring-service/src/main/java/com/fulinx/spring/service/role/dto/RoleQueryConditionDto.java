/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.role.dto;

import com.fulinx.spring.data.mysql.dao.podo.role.RoleListConditionPo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleQueryConditionDto extends RoleListConditionPo {
    @Serial
    private static final long serialVersionUID = -3907798773137841701L;
}
