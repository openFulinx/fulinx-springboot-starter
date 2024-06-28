/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.podo.user;

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
public class UserListResultDo implements Serializable {

    @Serial
    private static final long serialVersionUID = 615235140817012220L;

    @Schema(description = "User Id")
    private Integer id;

    @Schema(name = "Telephone")
    private String telephone;

    @Schema(name = "Email")
    private String email;

    @Schema(name = "Password")
    private String password;

    @Schema(name = "Salt")
    private String salt;

    @Schema(description = "Email Verification Status: 0 - Unverified, 1 - Verified")
    private Integer isEmailVerify;

    @Schema(name = "Nick Name")
    private String nickname;

    @Schema(name = "First Name")
    private String firstName;

    @Schema(name = "Last Name")
    private String lastName;

    @Schema(description = "Gender, 1 - Male, 2 - Female")
    private Integer gender;

    @Schema(name = "Post")
    private String post;

    @Schema(name = "Status, 0: Disabled ,1: Enabled")
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
