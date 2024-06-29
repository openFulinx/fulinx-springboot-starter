/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.service.user.impl;

import com.fulinx.spring.core.spring.exception.BusinessException;
import com.fulinx.spring.data.mysql.entity.TbUserEntity;
import com.fulinx.spring.data.mysql.entity.TbUserProfileEntity;
import com.fulinx.spring.data.mysql.enums.SimpleStatusEnum;
import com.fulinx.spring.data.mysql.service.TbUserEntityService;
import com.fulinx.spring.data.mysql.service.TbUserProfileEntityService;
import com.fulinx.spring.service.enums.ErrorMessageEnum;
import com.fulinx.spring.service.user.IUserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class IUserProfileServiceImpl implements IUserProfileService {

    private final TbUserProfileEntityService tbUserProfileEntityService;

    private final TbUserEntityService tbUserEntityService;

    @Lazy
    @Autowired
    public IUserProfileServiceImpl(TbUserProfileEntityService tbUserProfileEntityService, TbUserEntityService tbUserEntityService) {
        this.tbUserProfileEntityService = tbUserProfileEntityService;
        this.tbUserEntityService = tbUserEntityService;
    }


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Boolean updateUserProfiles(Integer userId, String firstName, String lastName, LocalDate birthday, Integer gender, String company, String post, String personalInformation) throws BusinessException {
        // 检查user状态是否是禁用的状态
        TbUserEntity tbUserEntity = tbUserEntityService.getById(userId);
        if (tbUserEntity.getStatus() == SimpleStatusEnum._Disabled.getIndex()) {
            log.warn("Update user failed，Failed Reason: this user has been disabled, userId={}", userId);
            throw new BusinessException(ErrorMessageEnum.USER_DISABLED.getMessage(), ErrorMessageEnum.USER_DISABLED.getIndex());
        }
        // 检查userProfile表是否有记录
        List<TbUserProfileEntity> tbUserProfileEntityList = tbUserProfileEntityService.lambdaQuery().eq(TbUserProfileEntity::getUserId, userId).list();
        Boolean isOk;
        if (tbUserProfileEntityList.size() == 0) {
            TbUserProfileEntity tbUserProfileEntity = new TbUserProfileEntity();

            tbUserProfileEntity.setGender(gender);
            tbUserProfileEntity.setPost(post);
            isOk = tbUserProfileEntityService.save(tbUserProfileEntity);
        } else {
            TbUserProfileEntity tbUserProfileEntity = tbUserProfileEntityList.get(0);
            tbUserProfileEntity.setGender(gender);
            tbUserProfileEntity.setPost(post);
            isOk = tbUserProfileEntityService.updateById(tbUserProfileEntity);
        }
        return isOk;
    }
}
