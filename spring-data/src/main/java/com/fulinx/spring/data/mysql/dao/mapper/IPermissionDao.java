/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.mapper;

import com.fulinx.spring.data.mysql.dao.podo.permission.PermissionListResultDo;
import com.fulinx.spring.data.mysql.dao.podo.systemUser.SystemUserRolePermissionResultDo;
import com.fulinx.spring.data.mysql.entity.TbPermissionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface IPermissionDao {

    List<PermissionListResultDo> list();

    TbPermissionEntity lockById(Serializable id);
    boolean truncateTable();

    boolean save(SystemUserRolePermissionResultDo systemUserRolePermissionResultDo);
}
