package com.fulinx.spring.web.framework.base;


import com.fulinx.spring.core.generic.AbstractController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseClientSideController extends AbstractController {

//    protected ClientSideUserModel getClientSideUserModel() throws BusinessException {
//        ClientSideUserModel clientSideUserModel = (ClientSideUserModel) SecurityContextUtils.getUserModel(ClientSideUserModel.class).orElseThrow(() -> {
//            log.error("{}，无法获取ClientSideUserModel", ErrorMessageEnum.SYSTEM_ERROR.getName());
//            return new BusinessException(ErrorMessageEnum.SYSTEM_ERROR.getName(), ErrorMessageEnum.SYSTEM_ERROR.getIndex());
//        });
//        return clientSideUserModel;
//    }
//
//    protected Integer getUserId() throws BusinessException {
//        return getClientSideUserModel().getUserId();
//    }
//
//    protected String getUsername() throws BusinessException {
//        return getClientSideUserModel().getUsername();
//    }
//
//    protected Integer getUserType() throws BusinessException {
//        return getClientSideUserModel().getUserType();
//    }

}
