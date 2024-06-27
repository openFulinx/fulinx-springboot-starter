/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.mapper;

import com.fulinx.spring.data.mysql.dao.podo.user.UserListConditionPo;
import com.fulinx.spring.data.mysql.dao.podo.user.UserListResultDo;
import com.fulinx.spring.data.mysql.entity.TbUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Mapper
public interface IUserDao {

    TbUserEntity lockById(Serializable id);

    List<TbUserEntity> lockByIds(Collection<? extends Serializable> idList);

    List<UserListResultDo> list(UserListConditionPo userListConditionPo);

    List<UserListResultDo> getUsers(UserListConditionPo userListConditionPo);

}
