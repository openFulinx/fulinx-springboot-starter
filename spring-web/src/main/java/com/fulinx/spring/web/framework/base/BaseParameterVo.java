package com.fulinx.spring.web.framework.base;


import com.fulinx.spring.core.generic.AbstractParameterVo;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseParameterVo extends AbstractParameterVo {

    @Parameter(name = "用户语言代码", required = true)
    @NotNull
    private String languageCode;
}
