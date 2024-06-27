/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.spring.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

@Slf4j
public final class ApplicationContextUtils {

    // Spring应用上下文环境
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     */
    public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        assert applicationContext != null;
        ApplicationContextUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        assert applicationContext != null;
        return applicationContext;
    }

    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        return applicationContext.getBean(requiredType);
    }
}
