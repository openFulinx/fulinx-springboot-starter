/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.web.controller.serverSide.systemUser.vo;

import com.fulinx.spring.web.framework.base.BaseParameterVo;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class SystemUserLoginVo extends BaseParameterVo {
    private static final long serialVersionUID = 2940639382819061525L;

    @Parameter(name = "用户名")
    @NotNull
    private String username;

    @Parameter(name = "密码")
    @NotBlank(message = "{password}")
    private String password;

    @Parameter(name = "图形验证码键")
    @NotBlank
    private String captchaKey;

    @Parameter(name = "图形验证码")
    @NotBlank
    private String captchaValue;

}
