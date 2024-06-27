/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.podo.permission;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionListResultDo implements Serializable {

    @Serial
    private static final long serialVersionUID = 341685551042266259L;

    @Parameter(name = "ID")
    private Integer id;

    @Parameter(name = "权限名称")
    private String permissionName;

    @Parameter(name = "权限父级ID")
    private Integer permissionParentId;

    @Parameter(name = "权限唯一标识符")
    private String permissionCode;

    @Parameter(name = "权限类型：1. 节点 2. 分支")
    private Integer permissionType;

    @Parameter(name = "权限描述")
    private String permissionDescription;

    @Parameter(name = "软删除标识")
    private Integer isDelete;

    @Parameter(name = "记录版本")
    private Integer recordVersion;

    @Parameter(name = "备注")
    private String remark;

    @Parameter(name = "创建者")
    private String recordCreateName;

    @Parameter(name = "更新者")
    private String recordUpdateName;

    @Parameter(name = "创建时间")
    private LocalDateTime recordCreateTime;

    @Parameter(name = "更新时间")
    private LocalDateTime recordUpdateTime;

    @Parameter(name = "子权限")
    private List<PermissionListResultDo> children;
}
