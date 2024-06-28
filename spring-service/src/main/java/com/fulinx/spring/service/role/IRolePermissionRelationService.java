/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.role;

import com.fulinx.spring.data.mysql.entity.TbRolePermissionRelationEntity;

import java.util.Optional;

public interface IRolePermissionRelationService {

    Optional<TbRolePermissionRelationEntity> create(Integer roleId, Integer permissionId);
}
