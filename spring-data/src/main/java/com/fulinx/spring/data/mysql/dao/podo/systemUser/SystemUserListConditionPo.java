/*
 * Copyright (c) MiNong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.podo.systemUser;

import io.swagger.v3.oas.annotations.Parameter;
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

    @Parameter(name = "主键ID")
    private Integer id;

    @Parameter(name = "用户名")
    private String username;

    @Parameter(name = "用户类型")
    private Integer userType;

    @Parameter(name = "手机号码")
    private String telephone;

    @Parameter(name = "认证启用状态，（1：启用，9：已禁用）")
    private Integer status;

    @Parameter(name = "软删除标识")
    private Integer isDelete;
}
