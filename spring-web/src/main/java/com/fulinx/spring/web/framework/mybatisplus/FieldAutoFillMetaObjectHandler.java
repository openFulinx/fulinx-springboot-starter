/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.web.framework.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.fulinx.spring.core.spring.security.utils.SecurityContextUtils;
import com.fulinx.spring.core.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FieldAutoFillMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("recordCreateName", getName(), metaObject);
        setFieldValByName("recordCreateTime", DateTimeUtils.getCurrentDateTime(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("recordUpdateName", getName(), metaObject);
        setFieldValByName("recordUpdateTime", DateTimeUtils.getCurrentDateTime(), metaObject);
    }

    private String getName() {
        String name;
        if (SecurityContextUtils.getUserModel().isPresent()) {
            name = SecurityContextUtils.getUserModel().get().getOperatorName();
        } else {
            name = "管理员";
        }
        return name;
    }
}
