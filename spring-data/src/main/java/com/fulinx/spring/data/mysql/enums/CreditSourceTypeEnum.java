/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.enums;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public enum CreditSourceTypeEnum {
    _文章(1, "文章"),
    _点赞(2, "点赞");

    @Getter
    private final String name;
    @Getter
    private final Integer index;
    @Getter
    private static final Map<Integer, CreditSourceTypeEnum> map = new LinkedHashMap<>();

    static {
        for (CreditSourceTypeEnum item : CreditSourceTypeEnum.values()) {
            map.put(item.getIndex(), item);
        }
    }

    CreditSourceTypeEnum(Integer index, String name) {
        this.name = name;
        this.index = index;
    }

    public static Optional<CreditSourceTypeEnum> of(Integer index) {
        return Optional.ofNullable(map.get(index));
    }

    public static boolean contains(Integer index) {
        return map.get(index) != null;
    }

    public static Optional<String> getName(Integer index) {
        CreditSourceTypeEnum e = map.get(index);
        return e == null ? Optional.empty() : Optional.of(e.name);
    }

    public static List<CreditSourceTypeEnum> getElementList() {
        return new ArrayList<>(map.values());
    }

    public static List<Integer> getIndexList() {
        return map.values().stream().map(CreditSourceTypeEnum::getIndex).collect(Collectors.toList());
    }

    public static List<String> getNameList() {
        return map.values().stream().map(CreditSourceTypeEnum::getName).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name;
    }
}
