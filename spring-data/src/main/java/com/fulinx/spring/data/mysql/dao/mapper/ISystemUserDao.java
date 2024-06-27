/*
 * Copyright (c) MiNong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.mapper;

import com.fulinx.spring.data.mysql.dao.podo.systemUser.SystemUserListConditionPo;
import com.fulinx.spring.data.mysql.dao.podo.systemUser.SystemUserListResultDo;
import com.fulinx.spring.data.mysql.entity.TbSystemUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface ISystemUserDao {

    TbSystemUserEntity lockById(Serializable id);

    List<SystemUserListResultDo> list(SystemUserListConditionPo userListConditionPo);

}
