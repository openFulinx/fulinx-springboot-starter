/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.web.framework.base;

import com.fulinx.spring.core.generic.AbstractController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseServerSideController extends AbstractController {

//    protected ServerSideUserModel getServerSideUserModel() throws BusinessException {
//        ServerSideUserModel webSystemUserModel = (ServerSideUserModel) SecurityContextUtils.getUserModel(ServerSideUserModel.class).orElseThrow(() -> {
//            log.error("{}，无法获取ServerSideUserModel", ErrorMessageEnum.SYSTEM_ERROR.getName());
//            return new BusinessException(ErrorMessageEnum.SYSTEM_ERROR.getName(), ErrorMessageEnum.SYSTEM_ERROR.getIndex());
//        });
//        return webSystemUserModel;
//    }
//
//    protected Integer getSystemUserId() throws BusinessException {
//        return getServerSideUserModel().getSystemUserId();
//    }
//
//    protected Integer getSystemUserType() throws BusinessException {
//        return getServerSideUserModel().getUserType();
//    }

}
