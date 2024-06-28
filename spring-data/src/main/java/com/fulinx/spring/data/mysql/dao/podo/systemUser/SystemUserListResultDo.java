/*
 * Copyright (c) MiNong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.podo.systemUser;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class SystemUserListResultDo implements Serializable {

    @Serial
    private static final long serialVersionUID = -3097863283384102285L;

    @Schema(description = "System User Id")
    private Integer id;

    @Schema(name = "User Role Ids")
    private String userRoleIds;

    @Schema(description = "First Name")
    private String firstName;

    @Schema(description = "Last Name")
    private String lastName;

    @Schema(description = "Gender, 1 - Male, 2 - Female")
    private Integer gender;

    @Schema(description = "Telephone")
    private String telephone;

    @Schema(description = "Post")
    private String post;

    @Schema(description = "Status, 0: Disable, 1: Enable")
    private Integer status;

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
