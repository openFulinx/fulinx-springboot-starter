/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.data.pagination.emums;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public enum PaginationMethodEnum {

    _经典模式(1, "经典模式"),
    _游标模式(2, "游标模式");

    @Getter
    private final String name;
    @Getter
    private final Integer index;
    @Getter
    private static final Map<Integer, PaginationMethodEnum> map = new LinkedHashMap<>();
    static{
        for (PaginationMethodEnum item : PaginationMethodEnum.values()) {
            map.put(item.getIndex(), item);
        }
    }

    PaginationMethodEnum(Integer index, String name) {
        this.name = name;
        this.index = index;
    }

    public static Optional<PaginationMethodEnum> of(Integer index) {
        return Optional.ofNullable(map.get(index));
    }

    public static boolean contains(Integer index) {
        return map.get(index) != null;
    }

    public static Optional<String> getName(Integer index) {
        PaginationMethodEnum e = map.get(index);
        return e == null ?  Optional.empty() : Optional.of(e.name);
    }

    public static List<PaginationMethodEnum> getElementList(){
        return new ArrayList<>(map.values());
    }

    public static List<Integer> getIndexList(){
        return map.values().stream().map(PaginationMethodEnum::getIndex).collect(Collectors.toList());
    }

    public static List<String> getNameList(){
        return map.values().stream().map(PaginationMethodEnum::getName).collect(Collectors.toList());
    }
}
