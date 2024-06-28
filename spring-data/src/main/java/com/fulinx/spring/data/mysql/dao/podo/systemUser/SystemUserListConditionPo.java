/*
 * Copyright (c) MiNong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.podo.systemUser;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 用户账户-查询条件实体
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemUserListConditionPo implements Serializable {

    @Serial
    private static final long serialVersionUID = -4381653809235656202L;

    @Schema(description = "System User Id")
    private Integer id;

    @Schema(description = "Username")
    private String username;

    @Schema(description = "User Type, 1: Normal, 9999: administrator")
    private Integer userType;

    @Schema(description = "Telephone")
    private String telephone;

    @Schema(description = "Status, 0: Disable, 1: Enable")
    private Integer status;

    @Schema(description = "Soft Delete Flag")
    private Integer isDelete;
}
