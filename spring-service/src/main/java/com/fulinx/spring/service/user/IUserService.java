/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fulinx.spring.core.spring.exception.BusinessException;
import com.fulinx.spring.data.mysql.entity.TbUserEntity;
import com.fulinx.spring.service.system.user.dto.SystemUserQueryConditionDto;
import com.fulinx.spring.service.system.user.dto.SystemUserQueryResultDto;
import com.fulinx.spring.service.user.dto.UserAuthenticationTokenDto;
import com.fulinx.spring.service.user.dto.UserPublicQueryResultDto;
import com.fulinx.spring.service.user.dto.UserQueryConditionDto;
import com.fulinx.spring.service.user.dto.UserTokenDto;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IUserService {

    Optional<TbUserEntity> lockById(Serializable id);

    Optional<TbUserEntity> getById(Integer id) throws BusinessException;

    /**
     * 登录
     *
     * @param telephone
     * @param password
     * @return
     * @throws BusinessException
     */
    UserTokenDto login(String telephone,
                       String password) throws BusinessException;

    /**
     * 登录
     *
     * @param telephone
     * @param password
     * @return
     * @throws BusinessException
     */
    UserTokenDto register(String telephone, String smsKey, String smsValue,
                          String password, String confirmPassword) throws BusinessException;

    /**
     * 刷新TOKEN
     *
     * @param refreshToken
     * @return
     * @throws BusinessException
     */
    UserAuthenticationTokenDto refreshToken(String refreshToken) throws BusinessException;

    /**
     * 指定账户进行更新密码
     *
     * @param id
     * @param newPassword 新密码
     * @return
     * @throws BusinessException
     */
    void updatePassword(Integer currentUserType, Integer id, String newPassword) throws BusinessException;

    void updatePassword(Integer id, String newPassword) throws BusinessException;

    /**
     * @param telephone
     * @param smsKey
     * @param smsValue
     * @param password
     * @param confirmPassword
     * @throws BusinessException
     */
    boolean resetPassword(String telephone, String smsKey, String smsValue,
                          String password, String confirmPassword) throws BusinessException;

    /**
     * 删除用户
     *
     * @param ids
     * @return
     * @throws BusinessException
     */
    boolean remove(List<Integer> ids) throws BusinessException;


    /**
     * 查询单个
     *
     * @param id
     * @return
     */
    Optional<SystemUserQueryResultDto> getUserById(Integer id) throws BusinessException;

    /**
     * 列表
     *
     * @param systemUserQueryConditionDto
     * @return
     */
    List<SystemUserQueryResultDto> list(SystemUserQueryConditionDto systemUserQueryConditionDto);

    IPage<UserPublicQueryResultDto> page(UserQueryConditionDto userQueryConditionDto, int pageNumber, int pageSize);

    /**
     * 列表-带分页
     *
     * @param systemUserQueryConditionDto
     * @param pageNumber
     * @param pageSize
     * @return
     */
    IPage<SystemUserQueryResultDto> page(SystemUserQueryConditionDto systemUserQueryConditionDto, int pageNumber, int pageSize);
    Boolean updateStatus(Integer userId, Integer status) throws BusinessException;

}
