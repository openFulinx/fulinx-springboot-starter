/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.user;

import com.fulinx.spring.core.spring.exception.BusinessException;

import java.time.LocalDate;

public interface IUserProfileService {

    Boolean updateUserNickname(Integer userId, String nickname) throws BusinessException;

    Boolean updateAvatar(Integer userId, Integer newAvatarFileId) throws BusinessException;

    Boolean updateUserProfiles(Integer userId, String firstName, String lastName, LocalDate birthday, Integer gender, String company, String post, String personalInformation) throws BusinessException;
}
