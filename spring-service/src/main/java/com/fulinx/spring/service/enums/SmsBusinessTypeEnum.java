package com.fulinx.spring.service.enums;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public enum SmsBusinessTypeEnum {

    _注册(1, "注册"),
    _找回密码(2, "找回密码");

    @Getter
    private final String name;
    @Getter
    private final Integer index;
    @Getter
    private static final Map<Integer, SmsBusinessTypeEnum> map = new LinkedHashMap<>();

    static {
        for (SmsBusinessTypeEnum item : SmsBusinessTypeEnum.values()) {
            map.put(item.getIndex(), item);
        }
    }

    SmsBusinessTypeEnum(Integer index, String name) {
        this.name = name;
        this.index = index;
    }

    public static Optional<SmsBusinessTypeEnum> of(Integer index) {
        return Optional.ofNullable(map.get(index));
    }

    public static boolean contains(Integer index) {
        return map.get(index) != null;
    }

    public static Optional<String> getName(Integer index) {
        SmsBusinessTypeEnum e = map.get(index);
        return e == null ? Optional.empty() : Optional.of(e.name);
    }

    public static List<SmsBusinessTypeEnum> getElementList() {
        return new ArrayList<>(map.values());
    }

    public static List<Integer> getIndexList() {
        return map.values().stream().map(SmsBusinessTypeEnum::getIndex).collect(Collectors.toList());
    }

    public static List<String> getNameList() {
        return map.values().stream().map(SmsBusinessTypeEnum::getName).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name;
    }
}
