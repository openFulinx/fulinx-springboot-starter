/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.enums;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public enum UserAvatarStatusEnum {
    _禁用(0, "Disabled"),
    _启用(1, "Enabled");

    @Getter
    private final String name;
    @Getter
    private final Integer index;
    @Getter
    private static final Map<Integer, UserAvatarStatusEnum> map = new LinkedHashMap<>();

    static {
        for (UserAvatarStatusEnum item : UserAvatarStatusEnum.values()) {
            map.put(item.getIndex(), item);
        }
    }

    UserAvatarStatusEnum(Integer index, String name) {
        this.name = name;
        this.index = index;
    }

    public static Optional<UserAvatarStatusEnum> of(Integer index) {
        return Optional.ofNullable(map.get(index));
    }

    public static boolean contains(Integer index) {
        return map.get(index) != null;
    }

    public static Optional<String> getName(Integer index) {
        UserAvatarStatusEnum e = map.get(index);
        return e == null ? Optional.empty() : Optional.of(e.name);
    }

    public static List<UserAvatarStatusEnum> getElementList() {
        return new ArrayList<>(map.values());
    }

    public static List<Integer> getIndexList() {
        return map.values().stream().map(UserAvatarStatusEnum::getIndex).collect(Collectors.toList());
    }

    public static List<String> getNameList() {
        return map.values().stream().map(UserAvatarStatusEnum::getName).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name;
    }
}
