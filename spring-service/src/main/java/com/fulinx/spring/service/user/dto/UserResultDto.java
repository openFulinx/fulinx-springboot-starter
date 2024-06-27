/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.user.dto;


import com.fulinx.spring.core.spring.security.model.AbstractUserModel;
import com.fulinx.spring.data.mysql.entity.TbFileEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserResultDto extends AbstractUserModel {

    @Serial
    private static final long serialVersionUID = -6945625794731096518L;

    private Integer userId;

    private Integer userType;

    private String telephone;

    private String username;

    private String email;

    private Integer isEmailVerify;

    private Integer userProfileId;

    private String nickname;

    private LocalDateTime avatarLastUpdateTime;

    private TbFileEntity avatarFileVo;

    private String firstName;

    private String lastName;

    private LocalDate birthday;

    private Integer gender;

    private String company;

    private String post;

    private String personalInformation;

}
