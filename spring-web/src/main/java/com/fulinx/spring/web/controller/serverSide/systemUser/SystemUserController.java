/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.web.controller.serverSide.systemUser;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fulinx.spring.core.generic.ResultListVo;
import com.fulinx.spring.core.generic.ResultVo;
import com.fulinx.spring.core.spring.exception.BusinessException;
import com.fulinx.spring.core.utils.MiscUtils;
import com.fulinx.spring.service.system.user.ISystemUserService;
import com.fulinx.spring.service.system.user.dto.SystemUserQueryConditionDto;
import com.fulinx.spring.service.system.user.dto.SystemUserQueryResultDto;
import com.fulinx.spring.web.controller.serverSide.systemUser.vo.*;
import com.fulinx.spring.web.framework.base.BaseServerSideController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "系统端-用户管理")
@Slf4j
@Validated
@RestController
@RequestMapping("/server-side/system/user")
public class SystemUserController extends BaseServerSideController {

    private final ISystemUserService iSystemUserService;

    public SystemUserController(ISystemUserService iSystemUserService) {
        this.iSystemUserService = iSystemUserService;
    }

    /**
     * 新增用户
     *
     * @param systemAdminUserCreateVo
     * @return
     * @throws BusinessException
     */
    @Operation(summary = "新增用户", method = "POST")
    @PostMapping
//    @PreAuthorize("hasAnyAuthority('sys:users','sys:users:add')")
    public ResultVo<Boolean> Create(@RequestBody @Valid SystemUserCreateVo systemAdminUserCreateVo) throws BusinessException {
//        Integer currentUserType = getSystemUserType();
//        String username = systemAdminUserCreateVo.getUsername();
//        String password = systemAdminUserCreateVo.getPassword();
//        List<Integer> roleIds = systemAdminUserCreateVo.getRoleIds();
//        String firstName = systemAdminUserCreateVo.getFirstName();
//        String lastName = systemAdminUserCreateVo.getLastName();
//        String telephone = systemAdminUserCreateVo.getTelephone();
//        String post = systemAdminUserCreateVo.getPost();
//        Integer gender = systemAdminUserCreateVo.getGender();
//        return ResultVo.build(iSystemUserService.create(currentUserType, username, password, systemAdminUserCreateVo.getUserType(), roleIds, firstName, lastName, gender, telephone, post, systemAdminUserCreateVo.getStatus()));
        return null;
    }

    /**
     * 删除用户
     *
     * @param systemAdminUserDeleteVo
     * @return
     * @throws BusinessException
     */
    @Operation(summary = "删除用户", method = "Delete")
    @DeleteMapping
//    @PreAuthorize("hasAnyAuthority('sys:users','sys:users:remove')")
    public ResultVo<Boolean> Delete(@RequestBody @Valid SystemUserDeleteVo systemAdminUserDeleteVo) throws BusinessException {
//        Integer currentUserType = getSystemUserType();
//        Integer currentUserId = getSystemUserId();
//        return ResultVo.build(iSystemUserService.remove(currentUserType, currentUserId, systemAdminUserDeleteVo.getIds()));
        return null;
    }

    /**
     * 更新用户
     *
     * @param id
     * @param systemAdminUserUpdateVo
     * @return
     * @throws BusinessException
     */
    @Operation(summary = "更新用户", method = "PUT")
    @PutMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('sys:users','sys:users:update')")
    public ResultVo<Boolean> Update(@PathVariable(value = "id") @Valid @NotNull @Min(1) Integer id, @RequestBody @Valid SystemUserUpdateVo systemAdminUserUpdateVo) throws BusinessException {
//        Integer currentUserType = getSystemUserType();
//        return ResultVo.build(iSystemUserService.update(currentUserType, id, systemAdminUserUpdateVo.getUsername(), systemAdminUserUpdateVo.getUserType(), systemAdminUserUpdateVo.getRoleIds(), systemAdminUserUpdateVo.getFirstName(), systemAdminUserUpdateVo.getLastName(), systemAdminUserUpdateVo.getGender(), systemAdminUserUpdateVo.getTelephone(), systemAdminUserUpdateVo.getPost(), systemAdminUserUpdateVo.getStatus()));
        return null;
    }

    /**
     * 获取用户基础信息
     *
     * @return
     * @throws BusinessException
     */
//    @ApiOperation(value = "获取用户基础信息", notes = "", httpMethod = "GET")
//    @GetMapping()
//    public ResultVo<ServerSideUserModel> Detail() throws BusinessException {
//        return ResultVo.build(getServerSideUserModel());
//    }
    @Operation(summary = "刷新Token", method = "POST")
    @PostMapping("/refresh/token")
    public ResultVo<?> refreshToken(@RequestBody @Valid SystemUserAuthenticationRefreshTokenParameterVo systemUserAuthenticationRefreshTokenParameterVo) throws BusinessException {
        log.debug("我是controller");
        return ResultVo.build(iSystemUserService.refreshToken(systemUserAuthenticationRefreshTokenParameterVo.getRefreshToken()));
    }

    /**
     * 查看用户
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    @Operation(summary = "查看用户", method = "GET")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('sys:users','sys:users:show')")
    public ResultVo<Optional<SystemUserQueryResultDto>> Show(@PathVariable(value = "id") @Valid @NotNull @Min(1) Integer id) throws BusinessException {
        return ResultVo.build(iSystemUserService.getById(id));
    }

    /**
     * 用户列表
     *
     * @param systemAdminUserPaginationParameterVo
     * @return
     * @throws BusinessException
     */
    @Operation(summary = "用户列表", method = "POST")
    @PostMapping("/pagination")
//    @PreAuthorize("hasAnyAuthority('sys:users','sys:users:pagination')")
    public ResultVo<ResultListVo<SystemUserQueryResultDto>> Pagination(@RequestBody @Valid SystemUserPaginationParameterVo systemAdminUserPaginationParameterVo) throws BusinessException {
        SystemUserQueryConditionDto systemUserQueryConditionDto = MiscUtils.copyProperties(systemAdminUserPaginationParameterVo, SystemUserQueryConditionDto.class);
        IPage<SystemUserQueryResultDto> systemUserQueryResultDtoIPage = iSystemUserService.page(systemUserQueryConditionDto, systemAdminUserPaginationParameterVo.getPageNumber(), systemAdminUserPaginationParameterVo.getPageSize());
        return ResultVo.build(ResultListVo.build(systemUserQueryResultDtoIPage.getRecords(), systemUserQueryResultDtoIPage.getTotal()));
    }


    @Operation(summary = "更改密码", method = "POST")
    @PostMapping("/update/password")
    public ResultVo<Void> UpdateUserPassword(@RequestBody @Valid SystemUserUpdatePasswordVo systemAdminUserUpdatePasswordVo) throws BusinessException {
//        Integer currentUserType = getSystemUserType();
//        Integer userId = systemAdminUserUpdatePasswordVo.getUserId();
//        String newPassword = systemAdminUserUpdatePasswordVo.getNewPassword();
//        iSystemUserService.updatePassword(currentUserType, userId, newPassword);
        return ResultVo.build();
    }


}
