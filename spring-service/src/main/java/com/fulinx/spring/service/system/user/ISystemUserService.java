/*
 * Copyright (c) MiNong Tech. 2023.
 */

package com.fulinx.spring.service.system.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fulinx.spring.core.spring.exception.BusinessException;
import com.fulinx.spring.data.mysql.entity.TbSystemUserEntity;
import com.fulinx.spring.service.system.user.dto.SystemUserAuthenticationTokenDto;
import com.fulinx.spring.service.system.user.dto.SystemUserQueryConditionDto;
import com.fulinx.spring.service.system.user.dto.SystemUserQueryResultDto;
import com.fulinx.spring.service.system.user.dto.SystemUserTokenDto;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface ISystemUserService {


    /**
     * 新增用户
     *
     * @param currentUserType
     * @param username
     * @param password
     * @param userType
     * @param roleIds
     * @param firstName
     * @param lastName
     * @param gender
     * @param telephone
     * @param post
     * @param status
     * @return
     * @throws BusinessException
     */
    boolean create(
            Integer currentUserType,
            String username,
            String password,
            Integer userType,
            List<Integer> roleIds,
            String firstName,
            String lastName,
            Integer gender,
            String telephone,
            String post, Integer status) throws BusinessException;

    /**
     * 删除用户
     *
     * @param ids
     * @return
     * @throws BusinessException
     */
    boolean remove(Integer currentUserType, Integer currentUserId, List<Integer> ids) throws BusinessException;

    /**
     * 修改用户
     *
     * @param currentUserType
     * @param id
     * @param username
     * @param userType
     * @param status
     * @param gender
     * @param telephone
     * @param post
     * @return
     * @throws BusinessException
     */
    boolean update(Integer currentUserType, Integer id, String username, Integer userType, List<Integer> roleIds, String firstName, String lastName, Integer gender, String telephone, String post, Integer status) throws BusinessException;


    /**
     * @param id
     * @return
     */
    Optional<TbSystemUserEntity> lockById(Serializable id);

    /**
     * 查询单个
     *
     * @param id
     * @return
     */
    Optional<SystemUserQueryResultDto> getById(Integer id) throws BusinessException;

    /**
     * 列表
     *
     * @param systemUserQueryConditionDto
     * @return
     */
    List<SystemUserQueryResultDto> list(SystemUserQueryConditionDto systemUserQueryConditionDto);

    /**
     * 列表-带分页
     *
     * @param systemUserQueryConditionDto
     * @param pageNumber
     * @param pageSize
     * @return
     */
    IPage<SystemUserQueryResultDto> page(SystemUserQueryConditionDto systemUserQueryConditionDto, int pageNumber, int pageSize);

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     * @throws BusinessException
     */
    SystemUserTokenDto login(String username,
                             String password, String captchaKey, String captchaValue) throws BusinessException;

    /**
     * 刷新TOKEN
     *
     * @param refreshToken
     * @return
     * @throws BusinessException
     */
    SystemUserAuthenticationTokenDto refreshToken(String refreshToken) throws BusinessException;

    /**
     * 指定账户进行更新密码
     *
     * @param id
     * @param newPassword 新密码
     * @return
     * @throws BusinessException
     */
    void updatePassword(Integer currentUserType, Integer id, String newPassword) throws BusinessException;

    /**
     * 指定登录账户进行重置密码
     *
     * @param id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @throws BusinessException
     */
    void resetPassword(Integer id, String oldPassword, String newPassword) throws BusinessException;

    /**
     * 校验用户名是否唯一
     *
     * @param username
     * @return
     * @throws BusinessException
     */
    boolean isUniqueUserName(String username) throws BusinessException;

    TbSystemUserEntity getByUserName(String username) throws BusinessException;
}
