/*
 * Copyright (c) MiNong Tech. 2023.
 */

package com.fulinx.spring.service.system.user;

import com.fulinx.spring.core.spring.exception.BusinessException;
import com.fulinx.spring.data.mysql.entity.TbSystemUserRoleRelationEntity;

import java.util.Optional;

public interface ISystemUserRoleRelationService {

    /**
     * 新增用户-角色关联关系
     *
     * @param systemUserId
     * @param roleId
     * @return
     * @throws BusinessException
     */
    Optional<TbSystemUserRoleRelationEntity> create(Integer systemUserId, Integer roleId) throws BusinessException;

    /**
     * 删除用户-角色关联关系
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    boolean remove(Integer id) throws BusinessException;

}
