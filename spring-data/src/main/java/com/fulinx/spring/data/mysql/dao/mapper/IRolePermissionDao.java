/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IRolePermissionDao {

    boolean truncateTable();
}
