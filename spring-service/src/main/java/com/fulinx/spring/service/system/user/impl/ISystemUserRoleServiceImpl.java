/*
 * Copyright (c) MiNong Tech. 2023.
 */

package com.fulinx.spring.service.system.user.impl;

import com.fulinx.spring.core.spring.exception.BusinessException;
import com.fulinx.spring.data.mysql.entity.TbSystemUserRoleEntity;
import com.fulinx.spring.data.mysql.service.TbSystemUserRoleEntityService;
import com.fulinx.spring.service.system.user.ISystemUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class ISystemUserRoleServiceImpl implements ISystemUserRoleService {

    private final TbSystemUserRoleEntityService tbSystemUserRoleEntityService;

    @Lazy
    @Autowired
    public ISystemUserRoleServiceImpl(TbSystemUserRoleEntityService tbSystemUserRoleEntityService) {
        this.tbSystemUserRoleEntityService = tbSystemUserRoleEntityService;
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
    public Optional<TbSystemUserRoleEntity> create(Integer systemUserId, Integer roleId) throws BusinessException {
        TbSystemUserRoleEntity tbSystemUserRoleEntity = new TbSystemUserRoleEntity();
        tbSystemUserRoleEntity.setSystemUserId(systemUserId);
        tbSystemUserRoleEntity.setRoleId(roleId);
        boolean isOk = tbSystemUserRoleEntityService.save(tbSystemUserRoleEntity);
        return Optional.ofNullable(isOk ? tbSystemUserRoleEntity : null);
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
        tbSystemUserRoleEntityService.removeById(id);
        return true;
    }
}
