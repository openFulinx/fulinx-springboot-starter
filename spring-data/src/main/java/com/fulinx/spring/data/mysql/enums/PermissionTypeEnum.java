/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.enums;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public enum PermissionTypeEnum {
    _查看用户列表(10000, "查看用户列表"),
    _查看用户资料(10001, "查看用户资料"),
    _添加用户(10002, "添加用户"),
    _修改用户(10003, "修改用户"),
    _删除用户(10004, "删除用户"),
    // 权限
    _查看权限列表(10005, "查看权限列表"),
    _查看权限(10006, "查看权限"),
    _添加权限(10007, "添加权限"),
    _修改权限(10008, "修改权限"),
    _删除权限(10009, "删除权限"),

    // 属性
    _查看属性列表(10005, "查看属性列表"),
    _查看属性(10006, "查看属性"),
    _添加属性(10007, "添加属性"),
    _修改属性(10008, "修改属性"),
    _删除属性(10009, "删除属性");

    @Getter
    private final String name;
    @Getter
    private final Integer index;
    @Getter
    private static final Map<Integer, PermissionTypeEnum> map = new LinkedHashMap<>();

    static {
        for (PermissionTypeEnum item : PermissionTypeEnum.values()) {
            map.put(item.getIndex(), item);
        }
    }

    PermissionTypeEnum(Integer index, String name) {
        this.name = name;
        this.index = index;
    }

    public static Optional<PermissionTypeEnum> of(Integer index) {
        return Optional.ofNullable(map.get(index));
    }

    public static boolean contains(Integer index) {
        return map.get(index) != null;
    }

    public static Optional<String> getName(Integer index) {
        PermissionTypeEnum e = map.get(index);
        return e == null ? Optional.empty() : Optional.of(e.name);
    }

    public static List<PermissionTypeEnum> getElementList() {
        return new ArrayList<>(map.values());
    }

    public static List<Integer> getIndexList() {
        return map.values().stream().map(PermissionTypeEnum::getIndex).collect(Collectors.toList());
    }

    public static List<String> getNameList() {
        return map.values().stream().map(PermissionTypeEnum::getName).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name;
    }
}
