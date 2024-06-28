/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.permission.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fulinx.spring.core.spring.exception.BusinessException;
import com.fulinx.spring.core.utils.MiscUtils;
import com.fulinx.spring.data.mysql.dao.mapper.IPermissionDao;
import com.fulinx.spring.data.mysql.dao.podo.permission.PermissionListResultDo;
import com.fulinx.spring.data.mysql.entity.TbPermissionEntity;
import com.fulinx.spring.data.mysql.service.TbPermissionEntityService;
import com.fulinx.spring.service.enums.ErrorMessageEnum;
import com.fulinx.spring.service.permission.IPermissionService;
import com.fulinx.spring.service.permission.dto.PermissionListResultDto;
import com.fulinx.spring.service.permission.dto.PermissionQueryConditionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class IPermissionServiceImpl implements IPermissionService {
    private TbPermissionEntityService tbPermissionEntityService;

    private final IPermissionDao iPermissionDao;

    @Lazy
    @Autowired
    public IPermissionServiceImpl(TbPermissionEntityService tbPermissionEntityService, IPermissionDao iPermissionDao) {
        this.tbPermissionEntityService = tbPermissionEntityService;
        this.iPermissionDao = iPermissionDao;
    }

    /**
     * 新增权限
     *
     * @param permissionName
     * @param permissionParentId
     * @param permissionCode
     * @param permissionType
     * @param description
     * @return
     */
    @Override
    public Optional<TbPermissionEntity> create(String permissionName, Integer permissionParentId, String permissionCode, Integer permissionType, String description) throws BusinessException {
        // 查询permissionCode是否存在，不包含自身
        boolean isUniquePermissionCode = this.isUniquePermissionCode(permissionCode);
        if (isUniquePermissionCode) {
            log.warn("The permission creation failed，failed reason:{}, permissionCode = {}", ErrorMessageEnum.PERMISSION_IDENTIFIER_DUPLICATE.getMessage(), permissionCode);
            throw new BusinessException(ErrorMessageEnum.PERMISSION_IDENTIFIER_DUPLICATE.getMessage(), ErrorMessageEnum.PERMISSION_IDENTIFIER_DUPLICATE.getIndex());
        }
        TbPermissionEntity tbPermissionEntity = new TbPermissionEntity();
        tbPermissionEntity.setPermissionParentId(permissionParentId);
        tbPermissionEntity.setPermissionCode(permissionCode);
        tbPermissionEntity.setPermissionType(permissionType);
        Boolean isOk = tbPermissionEntityService.save(tbPermissionEntity);
        return Optional.ofNullable(isOk ? tbPermissionEntity : null);
    }

    /**
     * 锁表查询单条记录
     *
     * @param id
     * @return
     */
    @Override
    public Optional<TbPermissionEntity> lockById(Serializable id) {
        return Optional.ofNullable(iPermissionDao.lockById(id));
    }

    /**
     * 获取单条记录
     *
     * @param id
     * @return
     */
    @Override
    public Optional<TbPermissionEntity> getById(Integer id) throws BusinessException {
        this.lockById(id).orElseThrow(() -> {
            log.warn("Failed to view permission, permission does not exist，id = {}", id);
            return new BusinessException(ErrorMessageEnum.PERMISSION_DATA_NOT_EXISTS.getMessage(), ErrorMessageEnum.PERMISSION_DATA_NOT_EXISTS.getIndex());
        });
        Optional<TbPermissionEntity> tbPermissionEntity = Optional.ofNullable(tbPermissionEntityService.getById(id));
        return tbPermissionEntity;
    }

    /**
     * 权限列表查询
     *
     * @param permissionQueryConditionDto
     * @return
     */
    @Override
    public List<PermissionListResultDto> list(PermissionQueryConditionDto permissionQueryConditionDto) {
        List<PermissionListResultDo> permissionListResultDoList = iPermissionDao.list();
        List<PermissionListResultDo> permissionListResultDos = MiscUtils.copyList(permissionListResultDoList, PermissionListResultDo.class);
        return MiscUtils.copyList(buildTree(permissionListResultDos, 0), PermissionListResultDto.class);
    }

    public static List<PermissionListResultDo> buildTree(List<PermissionListResultDo> list, Integer parentId) {
        List<PermissionListResultDo> tree = new ArrayList<>();
        for (PermissionListResultDo node : list) {
            if (Objects.equals(node.getPermissionParentId(), parentId)) {
                List<PermissionListResultDo> children = buildTree(list, node.getId());
                node.setChildren(children);
                tree.add(node);
            }
        }
        return tree;
    }

    /**
     * 权限列表带分页查询
     *
     * @param permissionQueryConditionDto
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public IPage<PermissionListResultDto> page(PermissionQueryConditionDto permissionQueryConditionDto, int pageNumber, int pageSize) {
        return null;
    }

    /**
     * 查询permissionCode是否存在
     *
     * @param permissionCode
     * @return
     * @throws BusinessException
     */
    @Override
    public boolean isUniquePermissionCode(String permissionCode) throws BusinessException {
        LambdaQueryWrapper<TbPermissionEntity> tbPermissionEntityLambdaQueryWrapper = Wrappers.lambdaQuery();
        tbPermissionEntityLambdaQueryWrapper.eq(TbPermissionEntity::getPermissionCode, permissionCode);
        long count = tbPermissionEntityService.count(tbPermissionEntityLambdaQueryWrapper);
        return count > 0 ? true : false;
    }
}
