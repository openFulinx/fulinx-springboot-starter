/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.permission;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fulinx.spring.core.spring.exception.BusinessException;
import com.fulinx.spring.data.mysql.entity.TbPermissionEntity;
import com.fulinx.spring.service.permission.dto.PermissionListResultDto;
import com.fulinx.spring.service.permission.dto.PermissionQueryConditionDto;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IPermissionService {

    /**
     * 新增权限
     * @param permissionName
     * @param permissionParentId
     * @param permissionCode
     * @param permissionType
     * @param description
     * @return
     */
    Optional<TbPermissionEntity> create(String permissionName, Integer permissionParentId, String permissionCode, Integer permissionType, String description) throws BusinessException;


    /**
     * 锁表查询单条记录
     * @param id
     * @return
     */
    Optional<TbPermissionEntity> lockById(Serializable id);

    /**
     * 获取单条记录
     * @param id
     * @return
     */
    Optional<TbPermissionEntity> getById(Integer id) throws BusinessException;

    /**
     * 权限列表查询
     * @param permissionQueryConditionDto
     * @return
     */
    List<PermissionListResultDto> list(PermissionQueryConditionDto permissionQueryConditionDto);

    /**
     * 权限列表带分页查询
     * @param permissionQueryConditionDto
     * @param pageNumber
     * @param pageSize
     * @return
     */
    IPage<PermissionListResultDto> page(PermissionQueryConditionDto permissionQueryConditionDto, int pageNumber, int pageSize);

    /**
     * 查询permissionCode是否存在
     * @param permissionCode
     * @return
     * @throws BusinessException
     */
    boolean isUniquePermissionCode(String permissionCode) throws BusinessException;
}
