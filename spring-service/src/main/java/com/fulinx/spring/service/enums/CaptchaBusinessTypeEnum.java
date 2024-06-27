package com.fulinx.spring.service.enums;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public enum CaptchaBusinessTypeEnum {

    _登录(1, "登录"),
    _注册(2, "注册"),
    _修改密码(3, "修改密码"),
    _找回密码(4, "找回密码");

    @Getter
    private final String name;
    @Getter
    private final Integer index;
    @Getter
    private static final Map<Integer, CaptchaBusinessTypeEnum> map = new LinkedHashMap<>();

    static {
        for (CaptchaBusinessTypeEnum item : CaptchaBusinessTypeEnum.values()) {
            map.put(item.getIndex(), item);
        }
    }

    CaptchaBusinessTypeEnum(Integer index, String name) {
        this.name = name;
        this.index = index;
    }

    public static Optional<CaptchaBusinessTypeEnum> of(Integer index) {
        return Optional.ofNullable(map.get(index));
    }

    public static boolean contains(Integer index) {
        return map.get(index) != null;
    }

    public static Optional<String> getName(Integer index) {
        CaptchaBusinessTypeEnum e = map.get(index);
        return e == null ? Optional.empty() : Optional.of(e.name);
    }

    public static List<CaptchaBusinessTypeEnum> getElementList() {
        return new ArrayList<>(map.values());
    }

    public static List<Integer> getIndexList() {
        return map.values().stream().map(CaptchaBusinessTypeEnum::getIndex).collect(Collectors.toList());
    }

    public static List<String> getNameList() {
        return map.values().stream().map(CaptchaBusinessTypeEnum::getName).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name;
    }
}
