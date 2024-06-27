/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.podo.role;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 用户账户-查询条件实体
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleListConditionPo implements Serializable {

    @Serial
    private static final long serialVersionUID = -467672439017601372L;

    @Parameter(name = "主键ID")
    private Integer id;

    @Parameter(name = "角色名称")
    private String roleName;

    @Parameter(name = "角色类型")
    private String roleType;

    @Parameter(name = "删除标识")
    private Boolean isDelete;

}
