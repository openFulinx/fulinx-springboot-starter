/*
 * Copyright (c) MiNong Tech. 2023.
 */

package com.fulinx.spring.service.system.user;

import com.fulinx.spring.data.mysql.entity.TbSystemUserProfileEntity;

import java.io.Serializable;
import java.util.Optional;

public interface ISystemUserProfileService {

    Optional<TbSystemUserProfileEntity> create(
            Integer systemUserId,
            String firstName,
            String lastName,
            Integer gender,
            String telephone,
            String post);

    Optional<TbSystemUserProfileEntity> lockById(Serializable id);

    Optional<TbSystemUserProfileEntity> lockByUserId(Serializable userId);


}
