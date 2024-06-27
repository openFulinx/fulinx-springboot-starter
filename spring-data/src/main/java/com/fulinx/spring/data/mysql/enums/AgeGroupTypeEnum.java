/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.enums;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public enum AgeGroupTypeEnum {
    _newBorn(1, "Newborn"),
    _infant(2, "Infant"),
    _toddler(3, "toddler"),
    _kids(4, "kids"),
    _adult(5, "adult");

    @Getter
    private final String name;
    @Getter
    private final Integer index;
    @Getter
    private static final Map<Integer, AgeGroupTypeEnum> map = new LinkedHashMap<>();

    static {
        for (AgeGroupTypeEnum item : AgeGroupTypeEnum.values()) {
            map.put(item.getIndex(), item);
        }
    }

    AgeGroupTypeEnum(Integer index, String name) {
        this.name = name;
        this.index = index;
    }

    public static Optional<AgeGroupTypeEnum> of(Integer index) {
        return Optional.ofNullable(map.get(index));
    }

    public static boolean contains(Integer index) {
        return map.get(index) != null;
    }

    public static Optional<String> getName(Integer index) {
        AgeGroupTypeEnum e = map.get(index);
        return e == null ? Optional.empty() : Optional.of(e.name);
    }

    public static List<AgeGroupTypeEnum> getElementList() {
        return new ArrayList<>(map.values());
    }

    public static List<Integer> getIndexList() {
        return map.values().stream().map(AgeGroupTypeEnum::getIndex).collect(Collectors.toList());
    }

    public static List<String> getNameList() {
        return map.values().stream().map(AgeGroupTypeEnum::getName).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name;
    }
}
