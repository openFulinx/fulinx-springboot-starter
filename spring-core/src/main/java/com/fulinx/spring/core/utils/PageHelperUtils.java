/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

public class PageHelperUtils {

    public static <E>PageInfo<E> toPageInfo(Page<E> page) {
        PageInfo<E> pageInfo = page.toPageInfo();
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

}
