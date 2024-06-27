/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.enums;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public enum GenderTypeEnum {
    _male(1, "male"),
    _female(2, "female"),
    _unisex(3, "unisex");

    @Getter
    private final String name;
    @Getter
    private final Integer index;
    @Getter
    private static final Map<Integer, GenderTypeEnum> map = new LinkedHashMap<>();

    static {
        for (GenderTypeEnum item : GenderTypeEnum.values()) {
            map.put(item.getIndex(), item);
        }
    }

    GenderTypeEnum(Integer index, String name) {
        this.name = name;
        this.index = index;
    }

    public static Optional<GenderTypeEnum> of(Integer index) {
        return Optional.ofNullable(map.get(index));
    }

    public static boolean contains(Integer index) {
        return map.get(index) != null;
    }

    public static Optional<String> getName(Integer index) {
        GenderTypeEnum e = map.get(index);
        return e == null ? Optional.empty() : Optional.of(e.name);
    }

    public static List<GenderTypeEnum> getElementList() {
        return new ArrayList<>(map.values());
    }

    public static List<Integer> getIndexList() {
        return map.values().stream().map(GenderTypeEnum::getIndex).collect(Collectors.toList());
    }

    public static List<String> getNameList() {
        return map.values().stream().map(GenderTypeEnum::getName).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name;
    }
}
