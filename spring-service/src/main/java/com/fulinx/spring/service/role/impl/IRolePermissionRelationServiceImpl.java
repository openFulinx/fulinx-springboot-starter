/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.role.impl;

import com.fulinx.spring.data.mysql.entity.TbRolePermissionRelationEntity;
import com.fulinx.spring.data.mysql.service.TbRolePermissionRelationEntityService;
import com.fulinx.spring.service.role.IRolePermissionRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IRolePermissionRelationServiceImpl implements IRolePermissionRelationService {
    private TbRolePermissionRelationEntityService tbRolePermissionRelationEntityService;

    @Lazy
    @Autowired
    public IRolePermissionRelationServiceImpl(TbRolePermissionRelationEntityService tbRolePermissionRelationEntityService) {
        this.tbRolePermissionRelationEntityService = tbRolePermissionRelationEntityService;
    }

    /**
     * 创建角色-权限关联关系
     *
     * @param roleId
     * @param permissionId
     * @return
     */
    @Override
    public Optional<TbRolePermissionRelationEntity> create(Integer roleId, Integer permissionId) {
        TbRolePermissionRelationEntity tbRolePermissionRelationEntity = new TbRolePermissionRelationEntity();
        tbRolePermissionRelationEntity.setRoleId(roleId);
        tbRolePermissionRelationEntity.setPermissionId(permissionId);
        boolean isOk = tbRolePermissionRelationEntityService.save(tbRolePermissionRelationEntity);
        return Optional.ofNullable(isOk ? tbRolePermissionRelationEntity : null);
    }
}
