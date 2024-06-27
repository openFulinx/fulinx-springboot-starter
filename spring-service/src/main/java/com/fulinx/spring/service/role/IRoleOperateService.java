/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.role;

import com.fulinx.spring.core.spring.exception.BusinessException;
import com.fulinx.spring.data.mysql.entity.TbRoleEntity;
import com.fulinx.spring.service.role.dto.RoleOneResultDto;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IRoleOperateService {

    /**
     * 创建角色
     * @param roleName
     * @param permissionIds
     * @return
     * @throws BusinessException
     */
    Integer create(String roleName, List<Integer> permissionIds) throws BusinessException;

    /**
     * 删除角色
     * @param ids
     * @return
     * @throws BusinessException
     */
    boolean remove(List<Integer> ids) throws BusinessException;

    /**
     * 修改角色
     * @param id
     * @param roleName
     * @param permissionIds
     * @param deletedPermissionIds
     * @return
     * @throws BusinessException
     */
    boolean update(Integer id, String roleName, List<Integer> permissionIds, List<Integer> deletedPermissionIds) throws BusinessException;

    /**
     * 锁表查询单条记录
     * @param id
     * @return
     */
    Optional<TbRoleEntity> lockById(Serializable id);

    /**
     * 获取单条记录
     * @param id
     * @return
     */
    Optional<RoleOneResultDto> getById(Integer id) throws BusinessException;

}
