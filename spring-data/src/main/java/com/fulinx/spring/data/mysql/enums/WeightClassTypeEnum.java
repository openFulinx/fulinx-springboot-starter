/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.enums;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public enum WeightClassTypeEnum {
    _g(1, "g"),
    _kg(2, "kg"),
    _lb(2, "lb");

    @Getter
    private final String name;
    @Getter
    private final Integer index;
    @Getter
    private static final Map<Integer, WeightClassTypeEnum> map = new LinkedHashMap<>();

    static {
        for (WeightClassTypeEnum item : WeightClassTypeEnum.values()) {
            map.put(item.getIndex(), item);
        }
    }

    WeightClassTypeEnum(Integer index, String name) {
        this.name = name;
        this.index = index;
    }

    public static Optional<WeightClassTypeEnum> of(Integer index) {
        return Optional.ofNullable(map.get(index));
    }

    public static boolean contains(Integer index) {
        return map.get(index) != null;
    }

    public static Optional<String> getName(Integer index) {
        WeightClassTypeEnum e = map.get(index);
        return e == null ? Optional.empty() : Optional.of(e.name);
    }

    public static List<WeightClassTypeEnum> getElementList() {
        return new ArrayList<>(map.values());
    }

    public static List<Integer> getIndexList() {
        return map.values().stream().map(WeightClassTypeEnum::getIndex).collect(Collectors.toList());
    }

    public static List<String> getNameList() {
        return map.values().stream().map(WeightClassTypeEnum::getName).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name;
    }
}
