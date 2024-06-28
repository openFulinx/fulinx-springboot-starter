/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.podo.systemUser;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fulinx.spring.data.mysql.entity.TbPermissionEntity;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SystemUserRolePermissionResultDo extends Model<TbPermissionEntity> {

    @Serial
    private static final long serialVersionUID = 2230454466162552723L;

    @Parameter(name = "ID")
    private Integer id;

    @Schema(description = "Permission Name")
    private String permissionName;

    @Schema(description = "Permission Parent Id")
    private Integer permissionParentId;

    @Schema(description = "Permission Unique Identifier")
    private String permissionCode;

    @Schema(description = "Permission Type: 1. Node 2. Branch")
    private Integer permissionType;

    @Schema(name = "Permission Description")
    private String permissionDescription;

    @Schema(name = "Children")
    private List<SystemUserRolePermissionResultDo> children;

    @Schema(description = "Soft Delete Flag")
    @TableLogic
    private Integer isDelete;

    @Schema(description = "Remark")
    private String remark;

    @Schema(description = "Record Version")
    @Version
    private Integer recordVersion;

    @Schema(description = "Record Create Name")
    private String recordCreateName;

    @Schema(description = "Record Update Name")
    private String recordUpdateName;

    @Schema(description = "Record Create Time")
    private LocalDateTime recordCreateTime;

    @Schema(description = "Record Update Time")
    private LocalDateTime recordUpdateTime;

}
