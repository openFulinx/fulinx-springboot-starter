/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.role.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fulinx.spring.core.spring.exception.BusinessException;
import com.fulinx.spring.core.utils.MiscUtils;
import com.fulinx.spring.data.mysql.dao.mapper.IRoleDao;
import com.fulinx.spring.data.mysql.dao.podo.role.RoleListConditionPo;
import com.fulinx.spring.data.mysql.dao.podo.role.RoleListResultDo;
import com.fulinx.spring.data.mysql.entity.TbRoleEntity;
import com.fulinx.spring.data.mysql.service.TbRoleEntityService;
import com.fulinx.spring.service.enums.ErrorMessageEnum;
import com.fulinx.spring.service.role.IRoleService;
import com.fulinx.spring.service.role.dto.RoleListResultDto;
import com.fulinx.spring.service.role.dto.RoleQueryConditionDto;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class IRoleServiceImpl implements IRoleService {
    private TbRoleEntityService tbRolesEntityService;

    private IRoleDao iRoleDao;

    @Lazy
    @Autowired
    public IRoleServiceImpl(TbRoleEntityService tbRolesEntityService, IRoleDao iRoleDao) {
        this.tbRolesEntityService = tbRolesEntityService;
        this.iRoleDao = iRoleDao;
    }

    /**
     * 新建Role
     *
     * @param roleName
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Optional<TbRoleEntity> create(String roleName) {
        TbRoleEntity tbRolesEntity = new TbRoleEntity();
        tbRolesEntity.setRoleName(roleName);
        Boolean isOk = tbRolesEntityService.save(tbRolesEntity);
        return Optional.ofNullable(isOk ? tbRolesEntity : null);
    }

    /**
     * 查看roleName是否唯一
     *
     * @param roleName
     * @return
     * @throws BusinessException
     */
    @Override
    public boolean isUniqueRoleName(String roleName) throws BusinessException {
        LambdaQueryWrapper<TbRoleEntity> tbRolesEntityLambdaQueryWrapper = Wrappers.lambdaQuery();
        tbRolesEntityLambdaQueryWrapper.eq(TbRoleEntity::getRoleName, roleName);
        long count = tbRolesEntityService.count(tbRolesEntityLambdaQueryWrapper);
        return count > 0 ? true : false;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<TbRoleEntity> getById(Integer id) throws BusinessException {
        this.lockById(id).orElseThrow(() -> {
            log.warn("View Role Failed，Role Does not exist，id = {}", id);
            return new BusinessException(ErrorMessageEnum.ROLE_NOT_EXISTS.getMessage(), ErrorMessageEnum.ROLE_NOT_EXISTS.getIndex());
        });
        TbRoleEntity tbRolesEntity = tbRolesEntityService.getById(id);
        return Optional.ofNullable(tbRolesEntity);
    }

    @Override
    public Optional<TbRoleEntity> lockById(Serializable id) {
        return Optional.ofNullable(iRoleDao.lockById(id));
    }

    @Override
    public IPage<RoleListResultDto> page(RoleQueryConditionDto roleQueryConditionDto, int pageNumber, int pageSize) {
        Page<RoleQueryConditionDto> page = PageHelper.startPage(pageNumber, pageSize);
        PageHelper.orderBy(String.format("%s desc", TbRoleEntity.ID));
        List<RoleListResultDto> roleListResultDtoList = list(roleQueryConditionDto);
        IPage<RoleListResultDto> roleListResultDtoPage = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>();
        roleListResultDtoPage.setTotal(page.getTotal());
        roleListResultDtoPage.setRecords(roleListResultDtoList);
        return roleListResultDtoPage;
    }

    @Override
    public List<RoleListResultDto> list(RoleQueryConditionDto roleQueryConditionDto) {
        RoleListConditionPo roleListConditionPo = MiscUtils.copyProperties(roleQueryConditionDto, RoleListConditionPo.class);
        List<RoleListResultDo> roleListResultDoList = iRoleDao.list(roleListConditionPo);
        return MiscUtils.copyList(roleListResultDoList, RoleListResultDto.class);
    }
}
