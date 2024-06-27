/*
 * Copyright (c) Xipu Tech. 2022.
 */

package com.fulinx.spring.web.controller.serverSide.systemUser.vo;

import com.fulinx.spring.web.framework.base.BaseParameterVo;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SystemUserCreateVo extends BaseParameterVo {

    @Parameter(name = "用户名", required = true)
    @NotBlank
    private String username;

    @Parameter(name = "密码", required = true)
    @NotBlank
    private String password;

    @Parameter(name = "用户类型", required = true)
    @NotNull
    private Integer userType;

    @Parameter(name = "角色ID列表", required = true)
    @NotEmpty
    private List<Integer> roleIds;

    @Parameter(name = "First Name")
    private String firstName;

    @Parameter(name = "Last Name")
    private String lastName;

    @Parameter(name = "手机号码")
    private String telephone;

    @Parameter(name = "职位")
    private String post;

    @Parameter(name = "性别")
    private Integer gender;

    @Parameter(name = "班次")
    private Integer shiftId;

    @Parameter(name = "状态", required = true)
    @NotNull
    private Integer status;
}
