/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.podo.permission;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Permission Id")
    private Integer id;

    @Schema(description = "Permission Name")
    private String permissionName;

    @Schema(description = "Permission Parent Id")
    private Integer permissionParentId;

    @Schema(description = "Permission Unique Identifier")
    private String permissionCode;

    @Schema(description = "Permission Type: 1. Node 2. Branch")
    private Integer permissionType;

    @Schema(description = "Permission Description")
    private String permissionDescription;

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

    @Schema(description = "Children")
    private List<PermissionListResultDo> children;
}
