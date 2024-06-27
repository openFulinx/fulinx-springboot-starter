/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.enums;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public enum SiteStatusEnum {
    _disabled(0, "Disabled"),
    _enabled(1, "Enabled"),
    _maintenance(3, "Maintenance");

    @Getter
    private final String name;
    @Getter
    private final Integer index;
    @Getter
    private static final Map<Integer, SiteStatusEnum> map = new LinkedHashMap<>();

    static {
        for (SiteStatusEnum item : SiteStatusEnum.values()) {
            map.put(item.getIndex(), item);
        }
    }

    SiteStatusEnum(Integer index, String name) {
        this.name = name;
        this.index = index;
    }

    public static Optional<SiteStatusEnum> of(Integer index) {
        return Optional.ofNullable(map.get(index));
    }

    public static boolean contains(Integer index) {
        return map.get(index) != null;
    }

    public static Optional<String> getName(Integer index) {
        SiteStatusEnum e = map.get(index);
        return e == null ? Optional.empty() : Optional.of(e.name);
    }

    public static List<SiteStatusEnum> getElementList() {
        return new ArrayList<>(map.values());
    }

    public static List<Integer> getIndexList() {
        return map.values().stream().map(SiteStatusEnum::getIndex).collect(Collectors.toList());
    }

    public static List<String> getNameList() {
        return map.values().stream().map(SiteStatusEnum::getName).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name;
    }
}
