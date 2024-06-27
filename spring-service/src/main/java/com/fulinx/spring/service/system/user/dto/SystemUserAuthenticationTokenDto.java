package com.fulinx.spring.service.system.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class SystemUserAuthenticationTokenDto {

    private String accessToken;

    private LocalDateTime accessTokenExpiration;

    private String refreshToken;

    private LocalDateTime refreshTokenExpiration;
}
