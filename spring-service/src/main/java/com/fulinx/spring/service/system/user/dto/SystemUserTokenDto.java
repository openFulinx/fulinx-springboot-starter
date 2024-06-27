/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.system.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
//@Schema(name = "认证返回DTO", description = "认证返回DTO")
public class SystemUserTokenDto {

    @Schema(description = "系统用户ID")
    private Integer systemUserId;

    private String accessToken;

    private LocalDateTime accessTokenExpiration;

    private String refreshToken;

    private LocalDateTime refreshTokenExpiration;

    private SystemUserResultDto userDetail;


}
