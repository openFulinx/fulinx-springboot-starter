package com.fulinx.spring.web.framework.base;


import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Schema(name = "BasePaginationParameterVo", description = "分页参数Vo")
@Data
@EqualsAndHashCode(callSuper = false)
public class BasePaginationParameterVo extends BaseParameterVo {

    @Serial
    private static final long serialVersionUID = 1978424152024752387L;

    @NotNull
    @Parameter(name = "页码", required = true)
    private Integer pageNumber;

    @NotNull
    @Parameter(name = "页面大小", required = true)
    private Integer pageSize;
}
