/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class UserTokenDto {

    private Integer userId;

    private String accessToken;

    private LocalDateTime accessTokenExpiration;

    private String refreshToken;

    private LocalDateTime refreshTokenExpiration;

    private UserResultDto userDetail;


}
