/*
 * Copyright (c) MiNong Tech. 2023.
 */

package com.fulinx.spring.service.system.user.impl;

import com.fulinx.spring.core.spring.exception.BusinessException;
import com.fulinx.spring.data.mysql.entity.TbSystemUserRoleRelationEntity;
import com.fulinx.spring.data.mysql.service.TbSystemUserRoleRelationEntityService;
import com.fulinx.spring.service.system.user.ISystemUserRoleRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class ISystemUserRoleRelationServiceImpl implements ISystemUserRoleRelationService {

    private final TbSystemUserRoleRelationEntityService tbSystemUserRoleRelationEntityService;

    @Lazy
    @Autowired
    public ISystemUserRoleRelationServiceImpl(TbSystemUserRoleRelationEntityService tbSystemUserRoleRelationEntityService) {
        this.tbSystemUserRoleRelationEntityService = tbSystemUserRoleRelationEntityService;
    }

    /**
     * 新增用户-角色关联关系
     *
     * @param systemUserId
     * @param roleId
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Optional<TbSystemUserRoleRelationEntity> create(Integer systemUserId, Integer roleId) throws BusinessException {
        TbSystemUserRoleRelationEntity tbSystemUserRoleRelationEntity = new TbSystemUserRoleRelationEntity();
        tbSystemUserRoleRelationEntity.setSystemUserId(systemUserId);
        tbSystemUserRoleRelationEntity.setRoleId(roleId);
        boolean isOk = tbSystemUserRoleRelationEntityService.save(tbSystemUserRoleRelationEntity);
        return Optional.ofNullable(isOk ? tbSystemUserRoleRelationEntity : null);
    }

    /**
     * 删除用户-角色关联关系
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    @Override
    public boolean remove(Integer id) throws BusinessException {
        tbSystemUserRoleRelationEntityService.removeById(id);
        return true;
    }
}
