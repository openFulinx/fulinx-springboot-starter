/*
 * Copyright (c) MiNong Tech. 2023.
 */

package com.fulinx.spring.service.system.user.impl;

import com.fulinx.spring.data.mysql.dao.mapper.ISystemUserProfileDao;
import com.fulinx.spring.data.mysql.entity.TbSystemUserProfileEntity;
import com.fulinx.spring.data.mysql.service.TbSystemUserProfileEntityService;
import com.fulinx.spring.service.system.user.ISystemUserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;

@Service
@Slf4j
public class ISystemUserProfileServiceImpl implements ISystemUserProfileService {


    private final TbSystemUserProfileEntityService tbSystemUserProfileEntityService;

    private final ISystemUserProfileDao iSystemUserProfileDao;

    @Lazy
    @Autowired
    public ISystemUserProfileServiceImpl(TbSystemUserProfileEntityService tbSystemUserProfileEntityService, ISystemUserProfileDao iSystemUserProfileDao) {
        this.tbSystemUserProfileEntityService = tbSystemUserProfileEntityService;
        this.iSystemUserProfileDao = iSystemUserProfileDao;
    }



    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Optional<TbSystemUserProfileEntity> create(Integer systemUserId, String firstName, String lastName, Integer gender, String telephone, String post) {
        TbSystemUserProfileEntity tbSystemUserProfileEntity = new TbSystemUserProfileEntity();
        tbSystemUserProfileEntity.setSystemUserId(systemUserId);
        tbSystemUserProfileEntity.setGender(gender);
        tbSystemUserProfileEntity.setTelephone(telephone);
        tbSystemUserProfileEntity.setPost(post);
        boolean isOk = tbSystemUserProfileEntityService.save(tbSystemUserProfileEntity);
        return Optional.ofNullable(isOk ? tbSystemUserProfileEntity : null);
    }

    @Override
    public Optional<TbSystemUserProfileEntity> lockById(Serializable id) {
        return Optional.ofNullable(iSystemUserProfileDao.lockById(id));
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public Optional<TbSystemUserProfileEntity> lockByUserId(Serializable userId) {
        return Optional.ofNullable(iSystemUserProfileDao.lockByUserId(userId));
    }

}
