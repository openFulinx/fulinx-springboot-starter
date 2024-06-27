/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.web.controller.share.captcha.vo;

import com.fulinx.spring.web.framework.base.BaseParameterVo;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class FetchCaptchaVo extends BaseParameterVo {

    @Parameter(name = "Captcha Type")
    @NotNull
    private Integer captchaType;

    @Parameter(name = "Captcha Key")
    @NotBlank
    private String captchaKey;
}
