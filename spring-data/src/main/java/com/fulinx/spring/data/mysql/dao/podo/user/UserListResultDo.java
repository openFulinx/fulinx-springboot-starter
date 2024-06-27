/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.podo.user;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserListResultDo implements Serializable {

    @Serial
    private static final long serialVersionUID = 615235140817012220L;

    @Parameter(name = "ID")
    private Integer id;

    @Parameter(name = "Telephone")
    private String telephone;

    @Parameter(name = "Email")
    private String email;

    @Parameter(name = "Password")
    private String password;

    @Parameter(name = "Salt")
    private String salt;

    @Parameter(name = "Is Email Verify, 0: No, 1: Yes")
    private Integer isEmailVerify;

    @Parameter(name = "Nick Name")
    private String nickname;

    @Parameter(name = "First Name")
    private String firstName;

    @Parameter(name = "Last Name")
    private String lastName;

    @Parameter(name = "Birthday")
    private LocalDate birthday;

    @Parameter(name = "Gender, 1: male, 2: female, 3: neutral, 4: private")
    private Integer gender;

    @Parameter(name = "Company")
    private String company;

    @Parameter(name = "Post")
    private String post;

    @Parameter(name = "Personal Information")
    @TableField("personal_information")
    private String personalInformation;

    @Parameter(name = "Status, 0: Disabled ,1: Enabled")
    private Integer status;

    @Parameter(name = "Soft Delete Flag")
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
