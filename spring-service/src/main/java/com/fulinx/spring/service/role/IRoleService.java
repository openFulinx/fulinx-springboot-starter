/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.role;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fulinx.spring.core.spring.exception.BusinessException;
import com.fulinx.spring.data.mysql.entity.TbRoleEntity;
import com.fulinx.spring.service.role.dto.RoleListResultDto;
import com.fulinx.spring.service.role.dto.RoleQueryConditionDto;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IRoleService {

    Optional<TbRoleEntity> create(String roleName);

    boolean isUniqueRoleName(String roleName) throws BusinessException;

    Optional<TbRoleEntity> getById(Integer id) throws BusinessException;

    Optional<TbRoleEntity> lockById(Serializable id);

    IPage<RoleListResultDto> page(RoleQueryConditionDto roleQueryConditionDto, int pageNumber, int pageSize);

    List<RoleListResultDto> list(RoleQueryConditionDto roleQueryConditionDto);
}
