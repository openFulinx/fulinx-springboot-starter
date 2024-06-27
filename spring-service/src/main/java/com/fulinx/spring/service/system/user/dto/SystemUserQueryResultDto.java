/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.system.user.dto;

import com.fulinx.spring.data.mysql.dao.podo.systemUser.SystemUserListResultDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = false)
public class SystemUserQueryResultDto extends SystemUserListResultDo {
    @Serial
    private static final long serialVersionUID = -2870381806293981067L;
}
