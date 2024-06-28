/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.role;

import com.fulinx.spring.data.mysql.entity.TbRolePermissionEntity;

import java.util.Optional;

public interface IRolePermissionService {

    Optional<TbRolePermissionEntity> create(Integer roleId, Integer permissionId);
}
