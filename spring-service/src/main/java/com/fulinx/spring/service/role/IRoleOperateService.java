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


    Integer create(String roleName, List<Integer> permissionIds) throws BusinessException;


    boolean remove(List<Integer> ids) throws BusinessException;


    boolean update(Integer id, String roleName, List<Integer> permissionIds, List<Integer> deletedPermissionIds) throws BusinessException;


    Optional<TbRoleEntity> lockById(Serializable id);


    Optional<RoleOneResultDto> getById(Integer id) throws BusinessException;

}
