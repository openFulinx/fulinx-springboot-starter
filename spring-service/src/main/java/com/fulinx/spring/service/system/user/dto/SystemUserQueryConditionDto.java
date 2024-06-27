/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.system.user.dto;


import com.fulinx.spring.data.mysql.dao.podo.systemUser.SystemUserListConditionPo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = false)
public class SystemUserQueryConditionDto extends SystemUserListConditionPo {

    @Serial
    private static final long serialVersionUID = 8241742345630923487L;
}
