package com.fulinx.spring.service.system.user.impl;

import com.baomidou.lock.annotation.Lock4j;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fulinx.spring.core.data.enums.RecordRemoveStatusEnum;
import com.fulinx.spring.core.spring.config.jackson.JacksonConfig;
import com.fulinx.spring.core.spring.exception.BusinessException;
import com.fulinx.spring.core.spring.security.jwt.emums.JwtTokenTypeEnum;
import com.fulinx.spring.core.spring.security.jwt.factory.JwtFactory;
import com.fulinx.spring.core.utils.DateTimeUtils;
import com.fulinx.spring.core.utils.IntegerUtils;
import com.fulinx.spring.core.utils.MessageSourceUtils;
import com.fulinx.spring.core.utils.MiscUtils;
import com.fulinx.spring.data.mysql.dao.mapper.ISystemUserDao;
import com.fulinx.spring.data.mysql.dao.podo.systemUser.SystemUserListConditionPo;
import com.fulinx.spring.data.mysql.dao.podo.systemUser.SystemUserListResultDo;
import com.fulinx.spring.data.mysql.entity.*;
import com.fulinx.spring.data.mysql.enums.SimpleStatusEnum;
import com.fulinx.spring.data.mysql.service.*;
import com.fulinx.spring.service.captcha.ICaptchaService;
import com.fulinx.spring.service.common.constant.DistributionLockNameConstant;
import com.fulinx.spring.service.enums.CaptchaBusinessTypeEnum;
import com.fulinx.spring.service.enums.ErrorMessageEnum;
import com.fulinx.spring.service.role.IRoleService;
import com.fulinx.spring.service.system.user.ISystemUserProfileService;
import com.fulinx.spring.service.system.user.ISystemUserRoleRelationService;
import com.fulinx.spring.service.system.user.ISystemUserService;
import com.fulinx.spring.service.system.user.dto.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ISystemUserServiceImpl implements ISystemUserService {

    private final ISystemUserRoleRelationService iSystemUserRoleRelationService;
    private final IRoleService iRoleService;

    private final ICaptchaService iCaptchaService;
    private final ISystemUserProfileService iSystemUserProfileService;
    private final TbSystemUserEntityService tbSystemUserEntityService;
    private final TbRoleEntityService tbRolesEntityService;
    private final TbSystemUserRoleRelationEntityService tbSystemUserRoleRelationEntityService;
    private final TbSystemUserProfileEntityService tbSystemUserProfileEntityService;
    private final ISystemUserDao iSystemUserDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TbRolePermissionRelationEntityService tbRolePermissionRelationEntityService;
    private final TbPermissionEntityService tbPermissionEntityService;
    private final JacksonConfig jacksonConfig;
    private final JwtFactory jwtFactory;
    private final MessageSourceUtils messageSourceUtils;

    @Autowired
    public ISystemUserServiceImpl(ISystemUserRoleRelationService iSystemUserRoleRelationService, IRoleService iRoleService, ICaptchaService iCaptchaService, ISystemUserProfileService iSystemUserProfileService, TbSystemUserEntityService tbSystemUserEntityService, TbRoleEntityService tbRolesEntityService, TbSystemUserRoleRelationEntityService tbSystemUserRoleRelationEntityService, TbSystemUserProfileEntityService tbSystemUserProfileEntityService, ISystemUserDao iSystemUserDao, BCryptPasswordEncoder bCryptPasswordEncoder, TbRolePermissionRelationEntityService tbRolePermissionRelationEntityService, TbPermissionEntityService tbPermissionEntityService, JacksonConfig jacksonConfig, JwtFactory jwtFactory, MessageSourceUtils messageSourceUtils) {
        this.iSystemUserRoleRelationService = iSystemUserRoleRelationService;
        this.iRoleService = iRoleService;
        this.iCaptchaService = iCaptchaService;
        this.iSystemUserProfileService = iSystemUserProfileService;
        this.tbSystemUserEntityService = tbSystemUserEntityService;
        this.tbRolesEntityService = tbRolesEntityService;
        this.tbSystemUserRoleRelationEntityService = tbSystemUserRoleRelationEntityService;
        this.tbSystemUserProfileEntityService = tbSystemUserProfileEntityService;
        this.iSystemUserDao = iSystemUserDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tbRolePermissionRelationEntityService = tbRolePermissionRelationEntityService;
        this.tbPermissionEntityService = tbPermissionEntityService;
        this.jacksonConfig = jacksonConfig;
        this.jwtFactory = jwtFactory;
        this.messageSourceUtils = messageSourceUtils;
    }


    /**
     * 创建新用户
     *
     * @param username
     * @param password
     * @param roleIds
     * @param gender
     * @param telephone
     * @param post
     * @return
     * @throws BusinessException
     */
    @Lock4j(name = DistributionLockNameConstant.USER_ADD_BY_ADMIN_LOCK, keys = {"#username"})
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean create(Integer currentUserType,
                          String username,
                          String password,
                          Integer userType,
                          List<Integer> roleIds,
                          String firstName,
                          String lastName,
                          Integer gender,
                          String telephone,
                          String post, Integer status) throws BusinessException {
        // 检查userType，当userType不为9999时，不允许新建超级管理员帐户
        if (currentUserType != 9999 && userType == 9999) {
            log.warn("Create User Failed,{},currentUserType={},userType={}", ErrorMessageEnum.NOT_ALLOW_ADD_SUPERADMIN.getMessage(), currentUserType, userType);
            throw new BusinessException(ErrorMessageEnum.NOT_ALLOW_ADD_SUPERADMIN.getMessage(), ErrorMessageEnum.NOT_ALLOW_ADD_SUPERADMIN.getIndex());
        }
        // 检查用户名是否唯一
        boolean isUsernameUnique = this.isUniqueUserName(username);
        if (isUsernameUnique) {
            log.warn("Create User Failed,{},username={}", ErrorMessageEnum.USERNAME_ALREADY_EXISTS.getMessage(), username);
            throw new BusinessException(ErrorMessageEnum.USERNAME_ALREADY_EXISTS.getMessage(), ErrorMessageEnum.USERNAME_ALREADY_EXISTS.getIndex());
        }
        // 新建用户
        String salt = String.valueOf(System.currentTimeMillis());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptPassword = bCryptPasswordEncoder.encode(StringUtils.join(password, salt));
        TbSystemUserEntity tbSystemUserEntity = new TbSystemUserEntity();
        tbSystemUserEntity.setUsername(username);
        tbSystemUserEntity.setPassword(encryptPassword);
        tbSystemUserEntity.setSalt(salt);
        tbSystemUserEntity.setUserType(userType);
        tbSystemUserEntity.setStatus(status);
        tbSystemUserEntityService.save(tbSystemUserEntity);

        for (Integer roleId : roleIds) {
            // 查看角色存不存在
            if (tbRolesEntityService.getById(roleId) == null) {
                log.error("Create User Failed，{}，roleId = {}", ErrorMessageEnum.ROLE_NOT_EXISTS.getMessage(), roleId);
                throw new BusinessException(ErrorMessageEnum.ROLE_NOT_EXISTS.getMessage(), ErrorMessageEnum.ROLE_NOT_EXISTS.getIndex());
            }
            // 新建用户与角色关联关系
            iSystemUserRoleRelationService.create(tbSystemUserEntity.getId(), roleId).orElseThrow(() -> {
                log.error("用户角色添加失败，{}, username={}, roleId={}", ErrorMessageEnum.USER_ROLE_ADD_FAIL.getMessage(), username, roleId);
                return new BusinessException(ErrorMessageEnum.USER_ROLE_ADD_FAIL.getMessage(), ErrorMessageEnum.USER_ADD_FAIL.getIndex());
            });
        }
        // 新增用户档案
        iSystemUserProfileService.create(tbSystemUserEntity.getId(), firstName, lastName, gender, telephone, post).orElseThrow(() -> {
            log.error("用户档案添加失败，{}, username={}", ErrorMessageEnum.USER_ARCHIVE_ADD_FAIL.getMessage(), username);
            return new BusinessException(ErrorMessageEnum.USER_ARCHIVE_ADD_FAIL.getMessage(), ErrorMessageEnum.USER_ARCHIVE_ADD_FAIL.getIndex());
        });

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
    public boolean remove(Integer currentUserType, Integer currentUserId, List<Integer> ids) throws BusinessException {
        // 检查userType，当userType不为9999时，不允许更新超级管理员帐户
        if (currentUserType != 9999) {
            log.warn("Delete User Failed,{}", ErrorMessageEnum.NOT_ALLOW_DELETE_SUPERADMIN.getMessage());
            throw new BusinessException(ErrorMessageEnum.NOT_ALLOW_DELETE_SUPERADMIN.getMessage(), ErrorMessageEnum.NOT_ALLOW_DELETE_SUPERADMIN.getIndex());
        }

        for (Integer id : ids
        ) {

            TbSystemUserEntity tbSystemUserEntity = this.lockById(id).orElseThrow(() -> {
                log.warn("Delete User Failed，user does not exist，id = {}", id);
                return new BusinessException(ErrorMessageEnum.USER_NOT_EXIST.getMessage(), ErrorMessageEnum.USER_NOT_EXIST.getIndex());
            });
            // 不允许删除自己
            if (id == currentUserId) {
                log.warn("Delete User Failed,{}", ErrorMessageEnum.NOT_ALLOW_DELETE_SELF.getMessage());
                throw new BusinessException(ErrorMessageEnum.NOT_ALLOW_DELETE_SELF.getMessage(), ErrorMessageEnum.NOT_ALLOW_DELETE_SELF.getIndex());
            }

            // 如果不是超级管理员，不允许删除超级管理员
            if (currentUserType != 9999 && tbSystemUserEntity.getUserType() == 9999) {
                log.warn("Delete User Failed,{}", ErrorMessageEnum.NOT_ALLOW_DELETE_SUPERADMIN.getMessage());
                throw new BusinessException(ErrorMessageEnum.NOT_ALLOW_DELETE_SUPERADMIN.getMessage(), ErrorMessageEnum.NOT_ALLOW_DELETE_SUPERADMIN.getIndex());
            }

            tbSystemUserEntity.setIsDelete(RecordRemoveStatusEnum._LOGICALLY_DELETED.getIndex());
            tbSystemUserEntityService.removeById(id);
            // 删除个人信息
            List<TbSystemUserProfileEntity> tbSystemUserProfileEntityList = listUserByUserId(id);
            TbSystemUserProfileEntity tbSystemUserProfileEntity = tbSystemUserProfileEntityList.get(0);
            tbSystemUserProfileEntity.setIsDelete(RecordRemoveStatusEnum._LOGICALLY_DELETED.getIndex());
            tbSystemUserProfileEntityService.removeById(tbSystemUserProfileEntity.getId());

            // 删除用户权限
            LambdaQueryWrapper<TbSystemUserRoleRelationEntity> tbSystemUserRoleRelationEntityLambdaQueryWrapper = Wrappers.lambdaQuery();
            tbSystemUserRoleRelationEntityLambdaQueryWrapper.eq(TbSystemUserRoleRelationEntity::getSystemUserId, id);
            List<TbSystemUserRoleRelationEntity> tbSystemUserRoleRelationEntityList = tbSystemUserRoleRelationEntityService.list(tbSystemUserRoleRelationEntityLambdaQueryWrapper);
            if (tbSystemUserRoleRelationEntityList.size() > 0) {
                for (TbSystemUserRoleRelationEntity tbSystemUserRoleRelationEntity : tbSystemUserRoleRelationEntityList
                ) {
                    tbSystemUserRoleRelationEntity.setIsDelete(RecordRemoveStatusEnum._LOGICALLY_DELETED.getIndex());
                    tbSystemUserRoleRelationEntityService.removeById(tbSystemUserRoleRelationEntity.getId());
                }
            }
        }

        return true;
    }

    /**
     * 修改用户
     *
     * @param id
     * @param username
     * @param status
     * @param gender
     * @param telephone
     * @param post
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean update(Integer currentUserType, Integer id, String username, Integer userType, List<Integer> roleIds, String firstName, String lastName, Integer gender, String telephone, String post, Integer status) throws BusinessException {
        // 检查userType，当userType不为9999时，不允许更新超级管理员帐户
        if (currentUserType != 9999 && userType == 9999) {
            log.warn("Update User Data Failed,{},currentUserType={},userType={}", ErrorMessageEnum.NOT_ALLOW_UPDATE_SUPERADMIN.getMessage(), currentUserType, userType);
            throw new BusinessException(ErrorMessageEnum.NOT_ALLOW_UPDATE_SUPERADMIN.getMessage(), ErrorMessageEnum.NOT_ALLOW_UPDATE_SUPERADMIN.getIndex());
        }
        TbSystemUserEntity tbSystemUserEntity = this.lockById(id).orElseThrow(() -> {
            log.warn("Update User Data Failed，user does not exist，id = {}", id);
            return new BusinessException(ErrorMessageEnum.USER_NOT_EXIST.getMessage(), ErrorMessageEnum.USER_NOT_EXIST.getIndex());
        });
        // 检查数据是否已重复（不包含自身）
        List<TbSystemUserEntity> tbSystemUserEntityList = listUserByUsername(username);
        if (CollectionUtils.isNotEmpty(tbSystemUserEntityList) && tbSystemUserEntityList.stream().anyMatch(p -> !IntegerUtils.equals(p.getId(), id))) {
            log.warn("Update User Data Failed，username has been exist，username={}", username);
            throw new BusinessException(ErrorMessageEnum.USER_UPDATE_FAIL.getMessage(), ErrorMessageEnum.USER_UPDATE_FAIL.getIndex());
        }
        tbSystemUserEntity.setUsername(username);
        tbSystemUserEntity.setStatus(status);
        tbSystemUserEntityService.updateById(tbSystemUserEntity);
        // 更新用户档案数据
        TbSystemUserProfileEntity tbSystemUserProfileEntity = iSystemUserProfileService.lockByUserId(id).orElseThrow(() -> {
            log.error("Update User Data Failed，user profile does not exist, id = {}", id);
            return new BusinessException(ErrorMessageEnum.USER_ARCHIVE_DATA_NOT_EXISTS.getMessage(), ErrorMessageEnum.USER_ARCHIVE_ADD_FAIL.getIndex());
        });
        tbSystemUserProfileEntity.setGender(gender);
        tbSystemUserProfileEntity.setTelephone(telephone);
        tbSystemUserProfileEntity.setPost(post);
        tbSystemUserProfileEntity.setTelephone(telephone);
        tbSystemUserProfileEntityService.updateById(tbSystemUserProfileEntity);

        // 先查询所有的角色，再新增
        LambdaQueryWrapper<TbSystemUserRoleRelationEntity> tbSystemUserRoleRelationEntityLambdaQueryWrapper = Wrappers.lambdaQuery();
        tbSystemUserRoleRelationEntityLambdaQueryWrapper.eq(TbSystemUserRoleRelationEntity::getSystemUserId, id);
        List<TbSystemUserRoleRelationEntity> tbSystemUserRoleRelationEntityList = tbSystemUserRoleRelationEntityService.list(tbSystemUserRoleRelationEntityLambdaQueryWrapper);
        // 存储已存在的角色ID
        Set<Integer> existingRoleIds = new HashSet<>();
        if (tbSystemUserRoleRelationEntityList.size() > 0) {
            for (TbSystemUserRoleRelationEntity tbSystemUserRoleRelationEntity : tbSystemUserRoleRelationEntityList
            ) {
                existingRoleIds.add(tbSystemUserRoleRelationEntity.getRoleId());
                // 如果旧的tbSystemUserRoleRelationEntity.getId()在roleIds中找不到，则删除
                if (!roleIds.contains(tbSystemUserRoleRelationEntity.getRoleId())) {
                    // 删除用户与角色关联关系
                    tbSystemUserRoleRelationEntity.setIsDelete(RecordRemoveStatusEnum._LOGICALLY_DELETED.getIndex());
                    tbSystemUserRoleRelationEntityService.removeById(tbSystemUserRoleRelationEntity.getId());
                }

            }
        }
        for (Integer roleId : roleIds) {
            // 查看角色是否存在
            if (!existingRoleIds.contains(roleId)) {
                if (tbRolesEntityService.getById(roleId) == null) {
                    log.error("Create User Failed，Role does not exist，roleId = {}", roleId);
                    throw new BusinessException(ErrorMessageEnum.ROLE_NOT_EXISTS.getMessage(), ErrorMessageEnum.ROLE_NOT_EXISTS.getIndex());
                }
                // 新建用户与角色关联关系
                TbSystemUserRoleRelationEntity newUserRole = new TbSystemUserRoleRelationEntity();
                newUserRole.setSystemUserId(id);
                newUserRole.setRoleId(roleId);
                tbSystemUserRoleRelationEntityService.save(newUserRole);
            }
        }
        return true;
    }


    @Override
    public Optional<TbSystemUserEntity> lockById(Serializable id) {
        return Optional.ofNullable(tbSystemUserEntityService.getById(id));
    }

    @Override
    public Optional<SystemUserQueryResultDto> getById(Integer id) throws BusinessException {
        SystemUserQueryConditionDto systemUserQueryConditionDto = new SystemUserQueryConditionDto();
        systemUserQueryConditionDto.setId(id);
        List<SystemUserQueryResultDto> systemUserQueryResultDtoList = this.list(systemUserQueryConditionDto);
        return Optional.ofNullable(systemUserQueryResultDtoList.size() > 0 ? systemUserQueryResultDtoList.get(0) : null);
    }

    @Override
    public List<SystemUserQueryResultDto> list(SystemUserQueryConditionDto systemUserQueryConditionDto) {
        SystemUserListConditionPo systemUserListConditionPo = MiscUtils.copyProperties(systemUserQueryConditionDto, SystemUserListConditionPo.class);
        List<SystemUserListResultDo> list = iSystemUserDao.list(systemUserListConditionPo);
        return MiscUtils.copyList(list, SystemUserQueryResultDto.class);
    }

    @Override
    public IPage<SystemUserQueryResultDto> page(SystemUserQueryConditionDto systemUserQueryConditionDto, int pageNumber, int pageSize) {
        Page<SystemUserListResultDo> page = PageHelper.startPage(pageNumber, pageSize);
        PageHelper.orderBy(String.format("%s desc", TbSystemUserEntity.ID));
        List<SystemUserQueryResultDto> systemUserQueryResultDtoList = list(systemUserQueryConditionDto);
        IPage<SystemUserQueryResultDto> systemUserQueryResultDtoIPage = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>();
        systemUserQueryResultDtoIPage.setTotal(page.getTotal());
        systemUserQueryResultDtoIPage.setRecords(systemUserQueryResultDtoList);
        return systemUserQueryResultDtoIPage;
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     * @throws BusinessException
     */
    @Override
    public SystemUserTokenDto login(String username, String password, String captchaKey, String captchaValue) throws BusinessException {
        boolean isMatch = iCaptchaService.matchCaptchaValue(captchaValue, captchaKey, CaptchaBusinessTypeEnum.LOGIN);
        if (!isMatch) {
            throw new BusinessException(ErrorMessageEnum.VERIFICATION_CODE_INCORRECT.getMessage(), ErrorMessageEnum.VERIFICATION_CODE_INCORRECT.getIndex());
        }
        // 清除REDIS中验证码
        iCaptchaService.flushCaptchaValue(captchaKey, CaptchaBusinessTypeEnum.LOGIN);

        // 查询用户是否存在
        LambdaQueryWrapper<TbSystemUserEntity> tbSystemUserEntityLambdaQueryWrapper = Wrappers.lambdaQuery();
        tbSystemUserEntityLambdaQueryWrapper.eq(TbSystemUserEntity::getUsername, username);
        List<TbSystemUserEntity> tbSystemUserEntityList = tbSystemUserEntityService.list(tbSystemUserEntityLambdaQueryWrapper);
        int count = tbSystemUserEntityList.size();
        // 如果列表为空，抛出错误
        if (CollectionUtils.isEmpty(tbSystemUserEntityList)) {
            log.debug("Login Failed,Failed Reason：{},username = {}, password = {}", ErrorMessageEnum.USER_NOT_EXIST.getMessage(), username, password);
            throw new BusinessException(ErrorMessageEnum.USER_NOT_EXIST.getMessage(), ErrorMessageEnum.USER_NOT_EXIST.getIndex());
        }
        // 如果列表Size大于1, 抛出错误
        if (count > 1) {
            log.debug("Login Failed,Failed Reason：{}, when user authentication，tb_users returns {} records， username = {}, password = {}", ErrorMessageEnum.SYSTEM_ERROR.getMessage(), count, username, password);
            throw new BusinessException(ErrorMessageEnum.SYSTEM_ERROR.getMessage(), ErrorMessageEnum.SYSTEM_ERROR.getIndex());
        }
        // 取出用户实例
        TbSystemUserEntity tbSystemUserEntity = tbSystemUserEntityList.get(0);
        Integer systemUserId = tbSystemUserEntity.getId();
        // 校验密码
        if (!bCryptPasswordEncoder.matches(StringUtils.join(password, tbSystemUserEntity.getSalt()), tbSystemUserEntity.getPassword())) {
            log.debug("Login Failed, Failed Reason:{}, username = {}, password = {}", ErrorMessageEnum.USERNAME_PASSWORD_INCORRECT.getMessage(), username, password);
            throw new BusinessException(ErrorMessageEnum.USERNAME_PASSWORD_INCORRECT.getMessage(), ErrorMessageEnum.USERNAME_PASSWORD_INCORRECT.getIndex());
        }
        // 查看用户状态是否在枚举中
        SimpleStatusEnum simpleStatusEnum = SimpleStatusEnum.of(tbSystemUserEntity.getStatus()).orElseThrow(() -> {
            log.error("Login Failed, Failed Reason: {}, status is not a valid value, username = {}, password = {} , status = {}", ErrorMessageEnum.SYSTEM_ERROR.getMessage(), tbSystemUserEntity.getUsername(), tbSystemUserEntity.getPassword(), tbSystemUserEntity.getStatus());
            return new BusinessException(ErrorMessageEnum.SYSTEM_ERROR.getMessage(), ErrorMessageEnum.SYSTEM_ERROR.getIndex());
        });
        // 状态状态是否为禁用
        if (SimpleStatusEnum._Disabled == simpleStatusEnum) {
            log.warn("Login Failed，Failed Reason：{}, username = {}, password = {}", ErrorMessageEnum.USER_DISABLED.getMessage(), username, password);
            throw new BusinessException(ErrorMessageEnum.USER_DISABLED.getMessage(), ErrorMessageEnum.USER_DISABLED.getIndex());
        }
        // 取出角色对应的权限
        LambdaQueryWrapper<TbSystemUserRoleRelationEntity> tbSystemUserRoleRelationEntityLambdaQueryWrapper = Wrappers.lambdaQuery();
        tbSystemUserRoleRelationEntityLambdaQueryWrapper.eq(TbSystemUserRoleRelationEntity::getSystemUserId, systemUserId);
        // 取出角色列表
        List<TbSystemUserRoleRelationEntity> tbSystemUserRoleRelationEntityList = tbSystemUserRoleRelationEntityService.list(tbSystemUserRoleRelationEntityLambdaQueryWrapper);
        StringBuffer authority = new StringBuffer();
        Set<Integer> systemUserRoleIds = new HashSet<>();
        Set<Integer> rolePermissions = new HashSet<>();
        if (tbSystemUserRoleRelationEntityList.size() > 0) {
            for (TbSystemUserRoleRelationEntity tbSystemUserRoleRelationEntity : tbSystemUserRoleRelationEntityList) {
                systemUserRoleIds.add(tbSystemUserRoleRelationEntity.getRoleId());
                // 取出角色对应的权限ID
                LambdaQueryWrapper<TbRolePermissionRelationEntity> tbRolePermissionRelationEntityLambdaQueryWrapper = Wrappers.lambdaQuery();
                tbRolePermissionRelationEntityLambdaQueryWrapper.eq(TbRolePermissionRelationEntity::getRoleId, tbSystemUserRoleRelationEntity.getRoleId());
                List<TbRolePermissionRelationEntity> tbRolePermissionRelationEntityList = tbRolePermissionRelationEntityService.list(tbRolePermissionRelationEntityLambdaQueryWrapper);
                if (tbRolePermissionRelationEntityList.size() > 0) {
                    for (TbRolePermissionRelationEntity tbRolePermissionRelationEntity : tbRolePermissionRelationEntityList) {
                        rolePermissions.add(tbRolePermissionRelationEntity.getPermissionId());
                    }
                }
            }
        }
        if (rolePermissions.size() > 0) {
            for (Integer rolePermission : rolePermissions) {
                TbPermissionEntity tbPermissionEntity = tbPermissionEntityService.getById(rolePermission);
                authority.append(tbPermissionEntity.getPermissionCode()).append(",");
            }
        }
        // 取出用户资料信息
        LambdaQueryWrapper<TbSystemUserProfileEntity> tbSystemUserProfileEntityLambdaQueryWrapper = Wrappers.lambdaQuery();
        tbSystemUserProfileEntityLambdaQueryWrapper.eq(TbSystemUserProfileEntity::getSystemUserId, systemUserId);
        TbSystemUserProfileEntity tbSystemUserProfileEntity = tbSystemUserProfileEntityService.getOne(tbSystemUserProfileEntityLambdaQueryWrapper);
        // 赋值给SystemUserResultDto
        SystemUserResultDto systemUserResultDto = new SystemUserResultDto();
        SystemUserAuthDto systemUserAuth = new SystemUserAuthDto();
        systemUserResultDto.setSystemUserId(tbSystemUserEntity.getId());
        systemUserResultDto.setSystemUserRoleIds(systemUserRoleIds);
        systemUserResultDto.setUserType(tbSystemUserEntity.getUserType());
        systemUserResultDto.setUserProfileId(tbSystemUserProfileEntity.getId());
        systemUserResultDto.setPost(tbSystemUserProfileEntity.getPost());
        systemUserResultDto.setTelephone(tbSystemUserProfileEntity.getTelephone());
        systemUserResultDto.setUsername(tbSystemUserEntity.getUsername());
        systemUserResultDto.setOperatorName(tbSystemUserEntity.getUsername());
        if (authority != null && authority.length() > 0) {
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authority.toString().substring(0, authority.length() - 1));
            systemUserResultDto.setAuthorities(grantedAuthorities);
        }
        systemUserAuth.setSystemUserId(tbSystemUserEntity.getId());
        systemUserAuth.setUserName(username);
        systemUserAuth.setSystemUserRoleIds(systemUserRoleIds);
        systemUserAuth.setOperatorName(username);
        try {
            String systemUserResultDtoJsonString = jacksonConfig.objectMapper().writeValueAsString(systemUserAuth);
            ClaimsBuilder accessTokenClaimsBuilder = jwtFactory.getClaimsBuilder(systemUserResultDtoJsonString, JwtTokenTypeEnum.AccessToken);
            ClaimsBuilder refreshTokenClaimsBuilder = jwtFactory.getClaimsBuilder(systemUserResultDtoJsonString, JwtTokenTypeEnum.RefreshToken);
            SystemUserTokenDto systemUserTokenDto = new SystemUserTokenDto();
            LocalDateTime currentDateTime = DateTimeUtils.getCurrentDateTime();
            systemUserTokenDto.setSystemUserId(tbSystemUserEntity.getId());
            systemUserTokenDto.setAccessToken(jwtFactory.createAccessToken(accessTokenClaimsBuilder.build(), currentDateTime));
            systemUserTokenDto.setAccessTokenExpiration(jwtFactory.getAccessTokenExpiration(currentDateTime));
            systemUserTokenDto.setRefreshToken(jwtFactory.createRefreshToken(refreshTokenClaimsBuilder.build(), currentDateTime));
            systemUserTokenDto.setRefreshTokenExpiration(jwtFactory.getRefreshTokenExpiration(currentDateTime));
            systemUserTokenDto.setUserDetail(systemUserResultDto);
            return systemUserTokenDto;
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new BusinessException(ErrorMessageEnum.SYSTEM_ERROR.getMessage(), ErrorMessageEnum.SYSTEM_ERROR.getIndex());
        }
    }

    /**
     * 刷新TOKEN
     *
     * @param refreshToken
     * @return
     * @throws BusinessException
     */
    @Override
    public SystemUserAuthenticationTokenDto refreshToken(String refreshToken) throws BusinessException {
        SecretKey signingKey = jwtFactory.createSigningKey();

        JwtParser jwtParser = Jwts.parser()
                .verifyWith(signingKey)
                .build();

        Jws<Claims> claimsJws = jwtParser.parseClaimsJws(refreshToken);

        Claims claims = claimsJws.getPayload();
        if (!jwtFactory.isRefreshToken(claims)) {
            log.error("The token is not refresh token, refreshToken={}", refreshToken);
            throw new BusinessException(ErrorMessageEnum.SYSTEM_ERROR.getMessage(), ErrorMessageEnum.SYSTEM_ERROR.getIndex());
        }

        SystemUserAuthenticationTokenDto systemUserAuthenticationTokenDto = new SystemUserAuthenticationTokenDto();
        LocalDateTime currentDateTime = DateTimeUtils.getCurrentDateTime();
        systemUserAuthenticationTokenDto.setAccessToken(jwtFactory.createAccessToken(claims, currentDateTime));
        systemUserAuthenticationTokenDto.setAccessTokenExpiration(jwtFactory.getAccessTokenExpiration(currentDateTime));
        systemUserAuthenticationTokenDto.setRefreshToken(jwtFactory.createRefreshToken(claims, currentDateTime));
        systemUserAuthenticationTokenDto.setRefreshTokenExpiration(jwtFactory.getRefreshTokenExpiration(currentDateTime));

        return systemUserAuthenticationTokenDto;
    }

    /**
     * 更改密码
     *
     * @param id
     * @param newPassword 新密码
     * @throws BusinessException
     */
    @Override
    public void updatePassword(Integer currentUserType, Integer id, String newPassword) throws BusinessException {
        // 检查userType，当userType不为9999时，不允许更新超级管理员帐户
        if (currentUserType != 9999) {
            log.warn("Update Password Failed,{},currentUserType={}", ErrorMessageEnum.NOT_ALLOW_UPDATE_SUPERADMIN.getMessage(), currentUserType);
            throw new BusinessException(ErrorMessageEnum.NOT_ALLOW_UPDATE_SUPERADMIN.getMessage(), ErrorMessageEnum.NOT_ALLOW_UPDATE_SUPERADMIN.getIndex());
        }
        // 查找用户是否存在
        TbSystemUserEntity tbSystemUserEntity = this.lockById(id).orElseThrow(() -> {
            log.error("修改密码失败，Failed Reason：{},id = {},  newPassword", ErrorMessageEnum.USER_NOT_EXIST.getMessage(), id, newPassword);
            return new BusinessException(ErrorMessageEnum.USER_NOT_EXIST.getMessage(), ErrorMessageEnum.USER_NOT_EXIST.getIndex());
        });
        String salt = String.valueOf(System.currentTimeMillis());
        String authenticationNewPassword = bCryptPasswordEncoder.encode(StringUtils.join(newPassword, salt));
        tbSystemUserEntity.setSalt(salt);
        tbSystemUserEntity.setPassword(authenticationNewPassword);
        tbSystemUserEntityService.updateById(tbSystemUserEntity);
    }

    /**
     * 重置密码
     *
     * @param id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @throws BusinessException
     */
    @Override
    public void resetPassword(Integer id, String oldPassword, String newPassword) throws BusinessException {
        // 查找用户是否存在
        TbSystemUserEntity tbSystemUserEntity = this.lockById(id).orElseThrow(() -> {
            log.error("Reset Password，Failed Reason：{},id = {}, oldPassword = {}, newPassword", ErrorMessageEnum.USER_NOT_EXIST.getMessage(), id, oldPassword, newPassword);
            return new BusinessException(ErrorMessageEnum.USER_NOT_EXIST.getMessage(), ErrorMessageEnum.USER_NOT_EXIST.getIndex());
        });
        // 校验旧密码是否正确
        if (!bCryptPasswordEncoder.matches(StringUtils.join(oldPassword, tbSystemUserEntity.getSalt()), tbSystemUserEntity.getPassword())) {
            log.debug("Reset Password，Failed Reason: {}, id = {}, oldPassword = {}, newPassword = {}", ErrorMessageEnum.USER_OLD_PASSWORD_INCORRECT.getMessage(), id, oldPassword, newPassword);
            throw new BusinessException(ErrorMessageEnum.USER_OLD_PASSWORD_INCORRECT.getMessage(), ErrorMessageEnum.USER_OLD_PASSWORD_INCORRECT.getIndex());
        }
        String salt = String.valueOf(System.currentTimeMillis());
        String authenticationNewPassword = bCryptPasswordEncoder.encode(StringUtils.join(newPassword, salt));
        tbSystemUserEntity.setSalt(salt);
        tbSystemUserEntity.setPassword(authenticationNewPassword);
        tbSystemUserEntityService.updateById(tbSystemUserEntity);
    }

    /**
     * 判断用户名是否唯一
     *
     * @param username
     * @return
     * @throws BusinessException
     */
    @Override
    public boolean isUniqueUserName(String username) throws BusinessException {
        LambdaQueryWrapper<TbSystemUserEntity> tbSystemUserEntityLambdaQueryWrapper = Wrappers.lambdaQuery();
        tbSystemUserEntityLambdaQueryWrapper.eq(TbSystemUserEntity::getUsername, username);
        long count = tbSystemUserEntityService.count(tbSystemUserEntityLambdaQueryWrapper);
        return count > 0 ? true : false;
    }

    /**
     * @param username
     * @return
     * @throws BusinessException
     */
    @Override
    public TbSystemUserEntity getByUserName(String username) throws BusinessException {
        LambdaQueryWrapper<TbSystemUserEntity> tbSystemUserEntityLambdaQueryWrapper = Wrappers.lambdaQuery();
        tbSystemUserEntityLambdaQueryWrapper.eq(TbSystemUserEntity::getUsername, username);
        TbSystemUserEntity tbSystemUserEntity = tbSystemUserEntityService.getOne(tbSystemUserEntityLambdaQueryWrapper);
        return tbSystemUserEntity;
    }

    /**
     * 根据用户ID查询
     *
     * @param id
     * @return
     */
    private List<TbSystemUserProfileEntity> listUserByUserId(Integer id) {
        LambdaQueryWrapper<TbSystemUserProfileEntity> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(TbSystemUserProfileEntity::getSystemUserId, id);
        return tbSystemUserProfileEntityService.list(lambdaQueryWrapper);
    }

    /**
     * 根据用户名查询
     *
     * @param Username
     * @return
     */
    private List<TbSystemUserEntity> listUserByUsername(String Username) {
        LambdaQueryWrapper<TbSystemUserEntity> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(TbSystemUserEntity::getUsername, Username);
        return tbSystemUserEntityService.list(lambdaQueryWrapper);
    }


}
