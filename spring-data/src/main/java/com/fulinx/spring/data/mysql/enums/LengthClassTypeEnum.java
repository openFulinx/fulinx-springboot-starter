/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.enums;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public enum LengthClassTypeEnum {
    _mm(1, "mm"),
    _cm(2, "cm"),
    _dm(3, "dm"),
    _m(4, "m"),
    _in(5, "in"),
    _ft(6, "ft");

    @Getter
    private final String name;
    @Getter
    private final Integer index;
    @Getter
    private static final Map<Integer, LengthClassTypeEnum> map = new LinkedHashMap<>();

    static {
        for (LengthClassTypeEnum item : LengthClassTypeEnum.values()) {
            map.put(item.getIndex(), item);
        }
    }

    LengthClassTypeEnum(Integer index, String name) {
        this.name = name;
        this.index = index;
    }

    public static Optional<LengthClassTypeEnum> of(Integer index) {
        return Optional.ofNullable(map.get(index));
    }

    public static boolean contains(Integer index) {
        return map.get(index) != null;
    }

    public static Optional<String> getName(Integer index) {
        LengthClassTypeEnum e = map.get(index);
        return e == null ? Optional.empty() : Optional.of(e.name);
    }

    public static List<LengthClassTypeEnum> getElementList() {
        return new ArrayList<>(map.values());
    }

    public static List<Integer> getIndexList() {
        return map.values().stream().map(LengthClassTypeEnum::getIndex).collect(Collectors.toList());
    }

    public static List<String> getNameList() {
        return map.values().stream().map(LengthClassTypeEnum::getName).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name;
    }
}
