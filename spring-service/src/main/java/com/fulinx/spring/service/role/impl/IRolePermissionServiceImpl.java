/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.role.impl;

import com.fulinx.spring.data.mysql.entity.TbRolePermissionEntity;
import com.fulinx.spring.data.mysql.service.TbRolePermissionEntityService;
import com.fulinx.spring.service.role.IRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IRolePermissionServiceImpl implements IRolePermissionService {
    private TbRolePermissionEntityService tbRolePermissionEntityService;

    @Lazy
    @Autowired
    public IRolePermissionServiceImpl(TbRolePermissionEntityService tbRolePermissionEntityService) {
        this.tbRolePermissionEntityService = tbRolePermissionEntityService;
    }

    /**
     * 创建角色-权限关联关系
     *
     * @param roleId
     * @param permissionId
     * @return
     */
    @Override
    public Optional<TbRolePermissionEntity> create(Integer roleId, Integer permissionId) {
        TbRolePermissionEntity tbRolePermissionEntity = new TbRolePermissionEntity();
        tbRolePermissionEntity.setRoleId(roleId);
        tbRolePermissionEntity.setPermissionId(permissionId);
        boolean isOk = tbRolePermissionEntityService.save(tbRolePermissionEntity);
        return Optional.ofNullable(isOk ? tbRolePermissionEntity : null);
    }
}
