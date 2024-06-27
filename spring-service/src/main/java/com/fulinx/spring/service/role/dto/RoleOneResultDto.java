/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.role.dto;

import com.fulinx.spring.data.mysql.dao.podo.role.RoleOneResultDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleOneResultDto extends RoleOneResultDo {
    @Serial
    private static final long serialVersionUID = 581434603281975685L;
}
