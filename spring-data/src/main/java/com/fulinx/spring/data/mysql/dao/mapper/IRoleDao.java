/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.mapper;

import com.fulinx.spring.data.mysql.dao.podo.role.RoleListConditionPo;
import com.fulinx.spring.data.mysql.dao.podo.role.RoleListResultDo;
import com.fulinx.spring.data.mysql.entity.TbRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface IRoleDao {

    TbRoleEntity lockById(Serializable id);

    List<RoleListResultDo> list(RoleListConditionPo roleListConditionPo);

    boolean truncateTable();

}
