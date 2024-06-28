/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.role.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fulinx.spring.core.data.enums.RecordRemoveStatusEnum;
import com.fulinx.spring.core.spring.exception.BusinessException;
import com.fulinx.spring.core.utils.IntegerUtils;
import com.fulinx.spring.core.utils.MiscUtils;
import com.fulinx.spring.data.mysql.dao.mapper.IRoleDao;
import com.fulinx.spring.data.mysql.entity.TbRoleEntity;
import com.fulinx.spring.data.mysql.entity.TbRolePermissionRelationEntity;
import com.fulinx.spring.data.mysql.entity.TbSystemUserRoleRelationEntity;
import com.fulinx.spring.data.mysql.service.TbRoleEntityService;
import com.fulinx.spring.data.mysql.service.TbRolePermissionRelationEntityService;
import com.fulinx.spring.data.mysql.service.TbSystemUserRoleRelationEntityService;
import com.fulinx.spring.service.enums.ErrorMessageEnum;
import com.fulinx.spring.service.permission.IPermissionService;
import com.fulinx.spring.service.role.IRoleOperateService;
import com.fulinx.spring.service.role.IRolePermissionRelationService;
import com.fulinx.spring.service.role.IRoleService;
import com.fulinx.spring.service.role.dto.RoleOneResultDto;
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
public class IRoleOperateServiceImpl implements IRoleOperateService {

    private IRoleDao iRoleDao;
    private IPermissionService iPermissionService;
    private IRoleService iRoleService;
    private IRolePermissionRelationService iRolePermissionRelationService;
    private TbRoleEntityService tbRolesEntityService;
    private TbRolePermissionRelationEntityService tbRolePermissionRelationEntityService;

    private TbSystemUserRoleRelationEntityService tbSystemUserRoleRelationEntityService;

    @Lazy
    @Autowired
    public IRoleOperateServiceImpl(IRoleDao iRoleDao, IPermissionService iPermissionService, IRoleService iRoleService, IRolePermissionRelationService iRolePermissionRelationService, TbRoleEntityService tbRolesEntityService, TbRolePermissionRelationEntityService tbRolePermissionRelationEntityService, TbSystemUserRoleRelationEntityService tbSystemUserRoleRelationEntityService) {
        this.iRoleDao = iRoleDao;
        this.iPermissionService = iPermissionService;
        this.iRoleService = iRoleService;
        this.iRolePermissionRelationService = iRolePermissionRelationService;
        this.tbRolesEntityService = tbRolesEntityService;
        this.tbRolePermissionRelationEntityService = tbRolePermissionRelationEntityService;
        this.tbSystemUserRoleRelationEntityService = tbSystemUserRoleRelationEntityService;
    }

    /**
     * 创建角色
     *
     * @param roleName
     * @param permissionIds
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Integer create(String roleName, List<Integer> permissionIds) throws BusinessException {
        // 校验权限名称是否存在
        boolean isUniqueRoleName = iRoleService.isUniqueRoleName(roleName);
        if (isUniqueRoleName) {
            log.error("Create Role Failed，failed reason, {}, roleName = {}", ErrorMessageEnum.ROLE_NAME_DUPLICATE, roleName);
            throw new BusinessException(ErrorMessageEnum.ROLE_NAME_DUPLICATE.getMessage(), ErrorMessageEnum.ROLE_NAME_DUPLICATE.getIndex());
        }
        // 检查权限列表是否为空
        if (permissionIds.isEmpty()) {
            log.error("Create Role Failed，failed reason, {}, roleName = {}", ErrorMessageEnum.ROLE_PERMISSION_CANNOT_BE_EMPTY, roleName);
            throw new BusinessException(ErrorMessageEnum.ROLE_PERMISSION_CANNOT_BE_EMPTY.getMessage(), ErrorMessageEnum.ROLE_PERMISSION_CANNOT_BE_EMPTY.getIndex());
        }
        TbRoleEntity tbRolesEntity = iRoleService.create(roleName).orElseThrow(() -> {
            log.error("Create Role Failed，failed reason，{}, roleName = {}", ErrorMessageEnum.ROLE_CREATE_FAIL.getMessage(), roleName);
            return new BusinessException(ErrorMessageEnum.ROLE_CREATE_FAIL.getMessage(), ErrorMessageEnum.ROLE_CREATE_FAIL.getIndex());
        });
        for (Integer permissionId : permissionIds) {
            // 判断permissionId是否在数据库中
            if (iPermissionService.getById(permissionId).isEmpty()) {
                log.error("Create Role Failed，failed reason, {}, permissionId = {}", ErrorMessageEnum.ROLE_CREATE_FAIL.getMessage(), permissionId);
                throw new BusinessException(ErrorMessageEnum.ROLE_CREATE_FAIL.getMessage(), ErrorMessageEnum.ROLE_CREATE_FAIL.getIndex());
            }
            iRolePermissionRelationService.create(tbRolesEntity.getId(), permissionId);
        }
        return tbRolesEntity.getId();
    }

    /**
     * 删除角色
     *
     * @param ids
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean remove(List<Integer> ids) throws BusinessException {
        for (Integer id : ids) {
            TbRoleEntity tbRolesEntity = iRoleService.lockById(id).orElseThrow(() -> {
                log.warn("Delete Role Failed，failed reason, id = {}", id);
                return new BusinessException(ErrorMessageEnum.ROLE_NOT_EXISTS.getMessage(), ErrorMessageEnum.ROLE_NOT_EXISTS.getIndex());
            });
            // 如果Role被引用，不允许删除
            LambdaQueryWrapper<TbSystemUserRoleRelationEntity> tbUserRolesEntityLambdaQueryWrapper = Wrappers.lambdaQuery();
            tbUserRolesEntityLambdaQueryWrapper.eq(TbSystemUserRoleRelationEntity::getRoleId, id);
            List<TbSystemUserRoleRelationEntity> tbUserRolesEntityList = tbSystemUserRoleRelationEntityService.list(tbUserRolesEntityLambdaQueryWrapper);
            if (tbUserRolesEntityList.size() > 0) {
                log.error("Delete Role Failed，failed reason: Role Has Been Referenced, id = {}", id);
                throw new BusinessException(ErrorMessageEnum.ROLE_REFERENCED.getMessage(), ErrorMessageEnum.ROLE_REFERENCED.getIndex());
            }

            tbRolesEntity.setIsDelete(RecordRemoveStatusEnum._LOGICALLY_DELETED.getIndex());
            tbRolesEntityService.removeById(id);
            List<TbRolePermissionRelationEntity> tbRolePermissionsEntities = listRolePermissionsByRoleId(id);
            for (TbRolePermissionRelationEntity tbRolePermissionRelationEntity : tbRolePermissionsEntities
            ) {
                Integer tbRolePermissionRelationEntityId = tbRolePermissionRelationEntity.getId();
                tbRolePermissionRelationEntity.setIsDelete(RecordRemoveStatusEnum._LOGICALLY_DELETED.getIndex());
                tbRolePermissionRelationEntityService.removeById(tbRolePermissionRelationEntityId);
            }
        }

        return true;
    }

    /**
     * 修改角色
     *
     * @param id
     * @param roleName
     * @param permissionIds
     * @param deletedPermissionIds
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean update(Integer id, String roleName, List<Integer> permissionIds, List<Integer> deletedPermissionIds) throws BusinessException {
        TbRoleEntity tbRolesEntity = iRoleService.lockById(id).orElseThrow(() -> {
            log.warn("Update Role Failed，failed reason: Role Does Not Exist, id = {}", id);
            return new BusinessException(ErrorMessageEnum.ROLE_NOT_EXISTS.getMessage(), ErrorMessageEnum.ROLE_NOT_EXISTS.getIndex());
        });
        // 检查数据是否已重复（不包含自身）
        List<TbRoleEntity> tbRolesEntities = listRolesByName(roleName);
        if (CollectionUtils.isNotEmpty(tbRolesEntities) && tbRolesEntities.stream().anyMatch(p -> !IntegerUtils.equals(p.getId(), id))) {
            log.warn("Update Role Failed，failed reason:，Role Name Has Been Used，roleName={}", roleName);
            throw new BusinessException(ErrorMessageEnum.ROLE_NAME_DUPLICATE.getMessage(), ErrorMessageEnum.ROLE_NAME_DUPLICATE.getIndex());
        }
        tbRolesEntity.setRoleName(roleName);
        tbRolesEntityService.updateById(tbRolesEntity);

        for (Integer permissionId : permissionIds
        ) {
            // 查看权限ID是否存在，不存在则新增
            List<TbRolePermissionRelationEntity> tbRolePermissionsEntities = listRolePermissionsByRoleIdAndPermissionId(id, permissionId);
            if (CollectionUtils.isEmpty(tbRolePermissionsEntities)) {
                iRolePermissionRelationService.create(id, permissionId);
            }
        }

        if (deletedPermissionIds != null && !deletedPermissionIds.isEmpty()) {
            // 删除不需要的权限
            for (Integer deletedPermissionId : deletedPermissionIds
            ) {
                List<TbRolePermissionRelationEntity> tbRolePermissionsEntities = listRolePermissionsByRoleIdAndPermissionId(id, deletedPermissionId);
                if (!CollectionUtils.isEmpty(tbRolePermissionsEntities)) {
                    TbRolePermissionRelationEntity tbRolePermissionRelationEntity = tbRolePermissionsEntities.get(0);
                    tbRolePermissionRelationEntity.setIsDelete(RecordRemoveStatusEnum._LOGICALLY_DELETED.getIndex());
                    tbRolePermissionRelationEntityService.removeById(tbRolePermissionRelationEntity.getId());
                }
            }
        }

        return true;
    }

    /**
     * 锁表查询单条记录
     *
     * @param id
     * @return
     */
    @Override
    public Optional<TbRoleEntity> lockById(Serializable id) {
        return Optional.ofNullable(iRoleDao.lockById(id));
    }

    /**
     * 获取权限内容
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    @Override
    public Optional<RoleOneResultDto> getById(Integer id) throws BusinessException {
        TbRoleEntity role = tbRolesEntityService.getById(id);
        if (role == null) {
            log.error("View Role Failed，failed reason:，Role does not exist,{}", ErrorMessageEnum.ROLE_NOT_EXISTS, id);
            throw new BusinessException(ErrorMessageEnum.ROLE_NOT_EXISTS.getMessage(), ErrorMessageEnum.ROLE_NOT_EXISTS.getIndex());
        }
        RoleOneResultDto oneResultDto = MiscUtils.copyProperties(role, RoleOneResultDto.class);
        LambdaQueryWrapper<TbRolePermissionRelationEntity> tbRolePermissionRelationEntityLambdaQueryWrapper = Wrappers.lambdaQuery();
        tbRolePermissionRelationEntityLambdaQueryWrapper.eq(TbRolePermissionRelationEntity::getRoleId, id);
        List<TbRolePermissionRelationEntity> tbRolePermissionsEntities = tbRolePermissionRelationEntityService.list(tbRolePermissionRelationEntityLambdaQueryWrapper);
        oneResultDto.setRolePermissions(tbRolePermissionsEntities);
        return Optional.ofNullable(oneResultDto == null ? null : oneResultDto);
    }


    /**
     * 根据角色名查询
     *
     * @param roleName
     * @return
     */
    private List<TbRoleEntity> listRolesByName(String roleName) {
        LambdaQueryWrapper<TbRoleEntity> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(TbRoleEntity::getRoleName, roleName);
        return tbRolesEntityService.list(lambdaQueryWrapper);
    }

    /**
     * @param roleId
     * @return
     */
    private List<TbRolePermissionRelationEntity> listRolePermissionsByRoleId(Integer roleId) {
        LambdaQueryWrapper<TbRolePermissionRelationEntity> tbRolePermissionRelationEntityLambdaQueryWrapper = Wrappers.lambdaQuery();
        tbRolePermissionRelationEntityLambdaQueryWrapper.eq(TbRolePermissionRelationEntity::getRoleId, roleId);
        return tbRolePermissionRelationEntityService.list(tbRolePermissionRelationEntityLambdaQueryWrapper);
    }

    /**
     * @param roleId
     * @param permissionId
     * @return
     */
    private List<TbRolePermissionRelationEntity> listRolePermissionsByRoleIdAndPermissionId(Integer roleId, Integer permissionId) {
        LambdaQueryWrapper<TbRolePermissionRelationEntity> tbRolePermissionRelationEntityLambdaQueryWrapper = Wrappers.lambdaQuery();
        tbRolePermissionRelationEntityLambdaQueryWrapper.eq(TbRolePermissionRelationEntity::getRoleId, roleId);
        tbRolePermissionRelationEntityLambdaQueryWrapper.eq(TbRolePermissionRelationEntity::getPermissionId, permissionId);
        return tbRolePermissionRelationEntityService.list(tbRolePermissionRelationEntityLambdaQueryWrapper);
    }

}
