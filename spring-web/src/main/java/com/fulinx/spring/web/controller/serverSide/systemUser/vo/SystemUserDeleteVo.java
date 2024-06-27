/*
 * Copyright (c) Xipu Tech. 2022.
 */

package com.fulinx.spring.web.controller.serverSide.systemUser.vo;

import com.fulinx.spring.web.framework.base.BaseParameterVo;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SystemUserDeleteVo extends BaseParameterVo {

    @Parameter(name = "用户ID数组", required = true)
    @NotEmpty
    private List<Integer> ids;
}
