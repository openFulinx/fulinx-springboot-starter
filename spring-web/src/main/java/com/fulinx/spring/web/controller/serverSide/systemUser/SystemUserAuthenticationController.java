
/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.web.controller.serverSide.systemUser;

import com.fulinx.spring.core.generic.ResultVo;
import com.fulinx.spring.core.spring.exception.BusinessException;
import com.fulinx.spring.service.system.user.ISystemUserService;
import com.fulinx.spring.service.system.user.dto.SystemUserTokenDto;
import com.fulinx.spring.web.controller.serverSide.systemUser.vo.SystemUserLoginVo;
import com.fulinx.spring.web.framework.base.BaseServerSideController;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "系统端-认证", description = "系统端-认证")
@Slf4j
@Validated
@RestController
@RequestMapping("/server-side/system-user/authentication")
public class SystemUserAuthenticationController extends BaseServerSideController {

    private final ISystemUserService iSystemUserService;

    @Autowired
    public SystemUserAuthenticationController(ISystemUserService iSystemUserService) {
        this.iSystemUserService = iSystemUserService;
    }

    /**
     * 用户登录
     *
     * @param systemAdminUserLoginVo
     * @return
     * @throws BusinessException
     */
    @Operation(summary = "用户登录", method = "POST")
    @ApiOperationSupport(order = 1)
//    @ApiResponse(description = "认证结果", responseCode = "200")
    @PostMapping("/login")
    @CrossOrigin
    public ResultVo<SystemUserTokenDto> Login(@RequestBody @Validated SystemUserLoginVo systemAdminUserLoginVo) throws BusinessException {
        String username = systemAdminUserLoginVo.getUsername();
        String password = systemAdminUserLoginVo.getPassword();
        SystemUserTokenDto systemUserTokenDto = iSystemUserService.login(username, password, systemAdminUserLoginVo.getCaptchaKey(), systemAdminUserLoginVo.getCaptchaValue());
        return ResultVo.build(systemUserTokenDto);
    }
}
