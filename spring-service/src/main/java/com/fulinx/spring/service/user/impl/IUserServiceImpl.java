/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fulinx.spring.core.spring.exception.BusinessException;
import com.fulinx.spring.core.spring.security.jwt.factory.JwtFactory;
import com.fulinx.spring.core.utils.DateTimeUtils;
import com.fulinx.spring.data.mysql.entity.TbUserEntity;
import com.fulinx.spring.data.mysql.service.TbUserEntityService;
import com.fulinx.spring.service.enums.ErrorMessageEnum;
import com.fulinx.spring.service.system.user.dto.SystemUserQueryConditionDto;
import com.fulinx.spring.service.system.user.dto.SystemUserQueryResultDto;
import com.fulinx.spring.service.user.IUserService;
import com.fulinx.spring.service.user.dto.UserAuthenticationTokenDto;
import com.fulinx.spring.service.user.dto.UserPublicQueryResultDto;
import com.fulinx.spring.service.user.dto.UserQueryConditionDto;
import com.fulinx.spring.service.user.dto.UserTokenDto;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class IUserServiceImpl implements IUserService {

    private final TbUserEntityService tbUsersEntityService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtFactory jwtFactory;

    @Autowired
    public IUserServiceImpl(TbUserEntityService tbUsersEntityService, BCryptPasswordEncoder bCryptPasswordEncoder, JwtFactory jwtFactory) {
        this.tbUsersEntityService = tbUsersEntityService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtFactory = jwtFactory;
    }


    @Override
    public Optional<TbUserEntity> lockById(Serializable id) {
        return Optional.ofNullable(tbUsersEntityService.getById(id));
    }

    @Override
    public Optional<TbUserEntity> getById(Integer id) throws BusinessException {
        this.lockById(id).orElseThrow(() -> {
            log.warn("View User Failed，User does not exist，id = {}", id);
            return new BusinessException(ErrorMessageEnum.USER_NOT_EXIST.getMessage(), ErrorMessageEnum.USER_NOT_EXIST.getIndex());
        });
        Optional<TbUserEntity> tbUsersEntity = Optional.ofNullable(tbUsersEntityService.getById(id));
        return tbUsersEntity;
    }

    /**
     * 登录
     *
     * @param telephone
     * @param password
     * @return
     * @throws BusinessException
     */
    @Override
    public UserTokenDto login(String telephone, String password) throws BusinessException {
        return null;
    }

    /**
     * 登录
     *
     * @param telephone
     * @param password
     * @param confirmPassword
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public UserTokenDto register(String telephone, String smsKey, String smsValue,
                                 String password, String confirmPassword) throws BusinessException {

        return null;
    }


    /**
     * 刷新TOKEN
     *
     * @param refreshToken
     * @return
     * @throws BusinessException
     */
    @Override
    public UserAuthenticationTokenDto refreshToken(String refreshToken) throws BusinessException {
        SecretKey signingKey = jwtFactory.createSigningKey();
        JwtParser jwtParser = Jwts.parser().verifyWith(signingKey).build();
        Jws<Claims> claimsJws = jwtParser.parseClaimsJws(refreshToken);
        Claims claims = claimsJws.getPayload();
        if (!jwtFactory.isRefreshToken(claims)) {
            log.error("The token is not refresh token, refreshToken={}", refreshToken);
            throw new BusinessException(ErrorMessageEnum.SYSTEM_ERROR.getMessage(), ErrorMessageEnum.SYSTEM_ERROR.getIndex());
        }

        UserAuthenticationTokenDto userAuthenticationTokenDto = new UserAuthenticationTokenDto();
        LocalDateTime currentDateTime = DateTimeUtils.getCurrentDateTime();
        userAuthenticationTokenDto.setAccessToken(jwtFactory.createAccessToken(claims, currentDateTime));
        userAuthenticationTokenDto.setAccessTokenExpiration(jwtFactory.getAccessTokenExpiration(currentDateTime));
        userAuthenticationTokenDto.setRefreshToken(jwtFactory.createRefreshToken(claims, currentDateTime));
        userAuthenticationTokenDto.setRefreshTokenExpiration(jwtFactory.getRefreshTokenExpiration(currentDateTime));

        return userAuthenticationTokenDto;
    }

    /**
     * 更改密码
     *
     * @param id
     * @param newPassword 新密码
     * @throws BusinessException
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updatePassword(Integer currentUserType, Integer id, String newPassword) throws BusinessException {
        // 检查userType，当userType不为9999时，不允许更新超级管理员帐户
        if (currentUserType != 9999) {
            log.warn("Update User Failed,{},currentUserType={}", ErrorMessageEnum.NOT_ALLOW_UPDATE_SUPERADMIN.getMessage(), currentUserType);
            throw new BusinessException(ErrorMessageEnum.NOT_ALLOW_UPDATE_SUPERADMIN.getMessage(), ErrorMessageEnum.NOT_ALLOW_UPDATE_SUPERADMIN.getIndex());
        }
        // 查找用户是否存在
        TbUserEntity tbUsersEntity = this.lockById(id).orElseThrow(() -> {
            log.error("Update Password Failed，Failed Reason：{},id = {},  newPassword", ErrorMessageEnum.USER_NOT_EXIST.getMessage(), id, newPassword);
            return new BusinessException(ErrorMessageEnum.USER_NOT_EXIST.getMessage(), ErrorMessageEnum.USER_NOT_EXIST.getIndex());
        });
        String salt = String.valueOf(System.currentTimeMillis());
        String authenticationNewPassword = bCryptPasswordEncoder.encode(StringUtils.join(newPassword, salt));
        tbUsersEntity.setSalt(salt);
        tbUsersEntity.setPassword(authenticationNewPassword);
        tbUsersEntityService.updateById(tbUsersEntity);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updatePassword(Integer id, String newPassword) throws BusinessException {
        // 查找用户是否存在
        TbUserEntity tbUsersEntity = this.lockById(id).orElseThrow(() -> {
            log.error("Update Password Failed，Failed Reason：{},id = {},  newPassword", ErrorMessageEnum.USER_NOT_EXIST.getMessage(), id, newPassword);
            return new BusinessException(ErrorMessageEnum.USER_NOT_EXIST.getMessage(), ErrorMessageEnum.USER_NOT_EXIST.getIndex());
        });
        String salt = String.valueOf(System.currentTimeMillis());
        String authenticationNewPassword = bCryptPasswordEncoder.encode(StringUtils.join(newPassword, salt));
        tbUsersEntity.setSalt(salt);
        tbUsersEntity.setPassword(authenticationNewPassword);
        tbUsersEntityService.updateById(tbUsersEntity);
    }

    /**
     * @param telephone
     * @param smsKey
     * @param smsValue
     * @param password
     * @param confirmPassword
     * @throws BusinessException
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean resetPassword(String telephone, String smsKey, String smsValue,
                                 String password, String confirmPassword) throws BusinessException {

        return true;
    }

    /**
     * 删除用户
     *
     * @param ids
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean remove(List<Integer> ids) throws BusinessException {

        return true;
    }


    /**
     * 查询单个
     *
     * @param id
     * @return
     */
    @Override
    public Optional<SystemUserQueryResultDto> getUserById(Integer id) throws BusinessException {
        this.lockById(id).orElseThrow(() -> {
            log.warn("View user failed，User does not exist，id = {}", id);
            return new BusinessException(ErrorMessageEnum.USER_NOT_EXIST.getMessage(), ErrorMessageEnum.USER_NOT_EXIST.getIndex());
        });
        SystemUserQueryConditionDto systemUserQueryConditionDto = new SystemUserQueryConditionDto();
        systemUserQueryConditionDto.setId(id);
        List<SystemUserQueryResultDto> list = this.list(systemUserQueryConditionDto);
        return Optional.ofNullable(!list.isEmpty() ? list.get(0) : null);
    }

    /**
     * 列表
     *
     * @param systemUserQueryConditionDto
     * @return
     */
    @Override
    public List<SystemUserQueryResultDto> list(SystemUserQueryConditionDto systemUserQueryConditionDto) {

        return null;
    }

    public List<UserPublicQueryResultDto> list(UserQueryConditionDto userQueryConditionDto) {
        return null;
    }

    @Override
    public IPage<UserPublicQueryResultDto> page(UserQueryConditionDto userQueryConditionDto, int pageNumber, int pageSize) {
        Page<UserQueryConditionDto> page = PageHelper.startPage(pageNumber, pageSize);
        PageHelper.orderBy(String.format("%s desc", TbUserEntity.ID));
        List<UserPublicQueryResultDto> list = list(userQueryConditionDto);
        IPage<UserPublicQueryResultDto> userPublicQueryResultDtoIPage = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>();
        userPublicQueryResultDtoIPage.setTotal(page.getTotal());
        userPublicQueryResultDtoIPage.setRecords(list);
        return userPublicQueryResultDtoIPage;
    }

    /**
     * 列表-带分页
     *
     * @param systemUserQueryConditionDto
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public IPage<SystemUserQueryResultDto> page(SystemUserQueryConditionDto systemUserQueryConditionDto, int pageNumber, int pageSize) {
        Page<SystemUserQueryConditionDto> page = PageHelper.startPage(pageNumber, pageSize);
        PageHelper.orderBy(String.format("%s desc", TbUserEntity.ID));
        List<SystemUserQueryResultDto> systemUserQueryResultDtoList = list(systemUserQueryConditionDto);
        IPage<SystemUserQueryResultDto> systemUserQueryResultDtoIPage = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>();
        systemUserQueryResultDtoIPage.setTotal(page.getTotal());
        systemUserQueryResultDtoIPage.setRecords(systemUserQueryResultDtoList);
        return systemUserQueryResultDtoIPage;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Boolean updateStatus(Integer userId, Integer status) throws BusinessException {
        TbUserEntity tbUserEntity = this.lockById(userId).orElseThrow(() -> {
            log.warn("Update User status failed，Failed Reason，user does not exist，id = {}", userId);
            return new BusinessException(ErrorMessageEnum.USER_NOT_EXIST.getMessage(), ErrorMessageEnum.USER_NOT_EXIST.getIndex());
        });
        tbUserEntity.setStatus(status);
        return tbUsersEntityService.updateById(tbUserEntity);
    }

    /**
     * 判断邮箱是否唯一
     *
     * @param email
     * @return
     * @throws BusinessException
     */
    private boolean isUniqueEmail(String email) throws BusinessException {
        LambdaQueryWrapper<TbUserEntity> tbUsersEntityLambdaQueryWrapper = Wrappers.lambdaQuery();
        tbUsersEntityLambdaQueryWrapper.eq(TbUserEntity::getEmail, email);
        long count = tbUsersEntityService.count(tbUsersEntityLambdaQueryWrapper);
        return count > 0 ? true : false;
    }

}
