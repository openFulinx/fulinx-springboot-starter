/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.enums;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public enum CreditChangeTypeEnum {
    _增加(1, "增加"),
    _删除(2, "删除");

    @Getter
    private final String name;
    @Getter
    private final Integer index;
    @Getter
    private static final Map<Integer, CreditChangeTypeEnum> map = new LinkedHashMap<>();

    static {
        for (CreditChangeTypeEnum item : CreditChangeTypeEnum.values()) {
            map.put(item.getIndex(), item);
        }
    }

    CreditChangeTypeEnum(Integer index, String name) {
        this.name = name;
        this.index = index;
    }

    public static Optional<CreditChangeTypeEnum> of(Integer index) {
        return Optional.ofNullable(map.get(index));
    }

    public static boolean contains(Integer index) {
        return map.get(index) != null;
    }

    public static Optional<String> getName(Integer index) {
        CreditChangeTypeEnum e = map.get(index);
        return e == null ? Optional.empty() : Optional.of(e.name);
    }

    public static List<CreditChangeTypeEnum> getElementList() {
        return new ArrayList<>(map.values());
    }

    public static List<Integer> getIndexList() {
        return map.values().stream().map(CreditChangeTypeEnum::getIndex).collect(Collectors.toList());
    }

    public static List<String> getNameList() {
        return map.values().stream().map(CreditChangeTypeEnum::getName).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name;
    }
}
