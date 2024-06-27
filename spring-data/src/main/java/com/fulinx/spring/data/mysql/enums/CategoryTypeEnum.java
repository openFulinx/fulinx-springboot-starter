/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.enums;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public enum CategoryTypeEnum {

    _系统分类(1, "系统分类"),
    _开放分类(2, "开放分类");

    @Getter
    private final String name;
    @Getter
    private final Integer index;
    @Getter
    private static final Map<Integer, CategoryTypeEnum> map = new LinkedHashMap<>();

    static {
        for (CategoryTypeEnum item : CategoryTypeEnum.values()) {
            map.put(item.getIndex(), item);
        }
    }

    CategoryTypeEnum(Integer index, String name) {
        this.name = name;
        this.index = index;
    }

    public static Optional<CategoryTypeEnum> of(Integer index) {
        return Optional.ofNullable(map.get(index));
    }

    public static boolean contains(Integer index) {
        return map.get(index) != null;
    }

    public static Optional<String> getName(Integer index) {
        CategoryTypeEnum e = map.get(index);
        return e == null ? Optional.empty() : Optional.of(e.name);
    }

    public static List<CategoryTypeEnum> getElementList() {
        return new ArrayList<>(map.values());
    }

    public static List<Integer> getIndexList() {
        return map.values().stream().map(CategoryTypeEnum::getIndex).collect(Collectors.toList());
    }

    public static List<String> getNameList() {
        return map.values().stream().map(CategoryTypeEnum::getName).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name;
    }
}
