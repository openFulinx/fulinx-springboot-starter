package com.fulinx.spring.web.framework.base;

import com.fulinx.spring.core.generic.AbstractResultVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseResultVo extends AbstractResultVo {
    private static final long serialVersionUID = -1098222604768954926L;
}
