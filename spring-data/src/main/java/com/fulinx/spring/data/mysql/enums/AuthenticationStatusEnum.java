/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.enums;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public enum AuthenticationStatusEnum {

    _已启用(1, "已启用"),
    _已禁用(9, "已禁用");

    @Getter
    private final String name;
    @Getter
    private final Integer index;
    @Getter
    private static final Map<Integer, AuthenticationStatusEnum> map = new LinkedHashMap<>();

    static {
        for (AuthenticationStatusEnum item : AuthenticationStatusEnum.values()) {
            map.put(item.getIndex(), item);
        }
    }

    AuthenticationStatusEnum(Integer index, String name) {
        this.name = name;
        this.index = index;
    }

    public static Optional<AuthenticationStatusEnum> of(Integer index) {
        return Optional.ofNullable(map.get(index));
    }

    public static boolean contains(Integer index) {
        return map.get(index) != null;
    }

    public static Optional<String> getName(Integer index) {
        AuthenticationStatusEnum e = map.get(index);
        return e == null ? Optional.empty() : Optional.of(e.name);
    }

    public static List<AuthenticationStatusEnum> getElementList() {
        return new ArrayList<>(map.values());
    }

    public static List<Integer> getIndexList() {
        return map.values().stream().map(AuthenticationStatusEnum::getIndex).collect(Collectors.toList());
    }

    public static List<String> getNameList() {
        return map.values().stream().map(AuthenticationStatusEnum::getName).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name;
    }
}
