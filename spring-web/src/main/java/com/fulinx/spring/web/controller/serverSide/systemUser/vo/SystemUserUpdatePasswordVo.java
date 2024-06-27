
/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.web.controller.serverSide.systemUser.vo;

import com.fulinx.spring.web.framework.base.BaseParameterVo;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class SystemUserUpdatePasswordVo extends BaseParameterVo {

    @Parameter(name = "用户ID")
    @NotNull
    private Integer userId;

    @Parameter(name = "新密码")
    @NotNull
    private String newPassword;
}
