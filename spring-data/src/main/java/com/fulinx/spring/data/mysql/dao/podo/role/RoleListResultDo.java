/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.podo.role;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleListResultDo implements Serializable {

    @Serial
    private static final long serialVersionUID = -7026957750457725266L;

    @Parameter(name = "ID")
    private Integer id;

    @Parameter(name = "角色名称")
    private String roleName;

    @Parameter(name = "软删除标识")
    private Integer isDelete;

    @Parameter(name = "备注")
    private String remark;

    @Parameter(name = "记录版本")
    private Integer recordVersion;

    @Parameter(name = "创建者")
    private String recordCreateName;

    @Parameter(name = "更新者")
    private String recordUpdateName;

    @Parameter(name = "创建时间")
    private LocalDateTime recordCreateTime;

    @Parameter(name = "更新时间")
    private LocalDateTime recordUpdateTime;
}
