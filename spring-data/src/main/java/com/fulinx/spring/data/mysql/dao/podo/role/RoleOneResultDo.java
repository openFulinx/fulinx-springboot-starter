/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.podo.role;

import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleOneResultDo implements Serializable {

    @Serial
    private static final long serialVersionUID = -2603549198404682336L;

    @Parameter(name = "ID")
    private Integer id;

    @Parameter(name = "角色类型")
    private Integer roleType;

    @Parameter(name = "角色名称")
    private String roleName;

    @Parameter(name = "权限ID列表")
    private List rolePermissions;

    @Parameter(name = "软删除标识")
    private Integer isDelete;

    @Parameter(name = "记录版本")
    @Version
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
}
