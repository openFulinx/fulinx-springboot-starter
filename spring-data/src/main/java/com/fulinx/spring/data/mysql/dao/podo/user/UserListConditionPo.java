/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.podo.user;

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
public class UserListConditionPo implements Serializable {

    @Serial
    private static final long serialVersionUID = 2846304759613907925L;

    @Parameter(name = "主键ID")
    private Integer id;

    @Parameter(name = "手机号码")
    private String telephone;

    @Parameter(name = "First Name")
    private String firstName;

    @Parameter(name = "Last Name")
    private String lastName;

    @Parameter(name = "认证启用状态，（1：启用，9：已禁用）")
    private Integer status;

    @Parameter(name = "软删除标识")
    private Integer isDelete;
}
