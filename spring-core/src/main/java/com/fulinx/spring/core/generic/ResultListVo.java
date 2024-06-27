/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.generic;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class ResultListVo<T> extends AbstractResultVo {

    private static final long serialVersionUID = -2044560447163357383L;
    private List<T> list;
    private long total;
    private ResultListVo(){
    }

    public static <T> ResultListVo<T> build(List<T> list, long total){
        ResultListVo<T> resultListVo = new ResultListVo<>();
        resultListVo.list = list;
        resultListVo.total = total;
        return resultListVo;
    }
}
