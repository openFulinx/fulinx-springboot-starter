/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.role;

import com.fulinx.spring.data.mysql.entity.TbRolePermissionEntity;

import java.util.Optional;

public interface IRolePermissionService {

    /**
     * 创建角色-权限关联关系
     *
     * @param roleId
     * @param permissionId
     * @return
     */
    Optional<TbRolePermissionEntity> create(Integer roleId, Integer permissionId);
}
