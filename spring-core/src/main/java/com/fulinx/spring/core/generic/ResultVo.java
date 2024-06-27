/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.generic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fulinx.spring.core.utils.DateTimeUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class ResultVo<T> extends AbstractResultVo {

    private static final long serialVersionUID = 3918835090287486456L;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime serverTime;
    private T data;
    private Integer errorCode;
    private String errorMessage;

    private ResultVo(){
    }


    public static <T> ResultVo<T> build(T data, String errorMessage, Integer errorCode){
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.data = data;
        resultVo.serverTime = DateTimeUtils.getCurrentDateTime();
        resultVo.errorCode = errorCode;
        resultVo.errorMessage = errorMessage;
        return resultVo;
    }

    public static <T> ResultVo<T> build(){
        return build(null, null, 0);
    }

    public static <T> ResultVo<T> build(T data){
        return build(data, null, 0);
    }

    public static <T> ResultVo<T> build(String errorMessage, Integer errorCode){
        return build(null, errorMessage, errorCode);
    }

    public static <T> ResultVo<T> build(List<String> errors){
        return build(null, String.join(",", errors), -1);
    }

    public static <T> ResultVo<ResultList<T>> build(Iterable<T> iterable, long total){
        return build(ResultList.build(iterable, total));
    }

    @Getter
    private static class ResultList<T> implements Serializable{

        private static final long serialVersionUID = 3154765487612822896L;

        private Iterable<T> list;
        private long total;
        private ResultList(){
        }

        public static <T>ResultList<T> build(Iterable<T> iterable, long total){
            ResultList<T> resultList = new ResultList<>();
            resultList.list = iterable;
            resultList.total = total;
            return resultList;
        }
    }
}