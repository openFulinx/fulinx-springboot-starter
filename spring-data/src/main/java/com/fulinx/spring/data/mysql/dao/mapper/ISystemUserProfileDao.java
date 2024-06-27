/*
 * Copyright (c) MiNong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.mapper;

import com.fulinx.spring.data.mysql.entity.TbSystemUserProfileEntity;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

@Mapper
public interface ISystemUserProfileDao {

    TbSystemUserProfileEntity lockById(Serializable id);

    TbSystemUserProfileEntity lockByUserId(Serializable userId);

}
