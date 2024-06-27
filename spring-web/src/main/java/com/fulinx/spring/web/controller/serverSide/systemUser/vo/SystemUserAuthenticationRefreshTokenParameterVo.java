package com.fulinx.spring.web.controller.serverSide.systemUser.vo;

import com.fulinx.spring.web.framework.base.BaseParameterVo;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SystemUserAuthenticationRefreshTokenParameterVo extends BaseParameterVo {
    private static final long serialVersionUID = 7755412827794330157L;

    @Parameter(name = "刷新用Token")
    @NotBlank
    private String refreshToken;
}
