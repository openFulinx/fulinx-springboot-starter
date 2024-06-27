/*
 * 角色列表Vo
 * Copyright (c) Xipu Tech. 2022.
 */

package com.fulinx.spring.web.controller.serverSide.systemUser.vo;

import com.fulinx.spring.web.framework.base.BasePaginationParameterVo;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "管理员分页查询参数")
public class SystemUserPaginationParameterVo extends BasePaginationParameterVo {

    @Parameter(name = "主键ID")
    private Integer id;

    @Parameter(name = "用户名")
    private String username;

    @Parameter(name = "手机号码")
    private String telephone;

    @Parameter(name = "用户类型")
    private Integer userType;

    @Parameter(name = "认证启用状态，（1：启用，9：已禁用）")
    private Integer status;

    @Parameter(name = "软删除标识")
    private Integer isDelete;

}
