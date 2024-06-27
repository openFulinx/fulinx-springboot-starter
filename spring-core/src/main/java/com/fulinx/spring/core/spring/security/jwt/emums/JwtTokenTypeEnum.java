/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.spring.security.jwt.emums;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public enum JwtTokenTypeEnum {

    AccessToken(1, "AccessToken"),
    RefreshToken(2, "RefreshToken");

    @Getter
    private final String name;
    @Getter
    private final Integer index;
    @Getter
    private static final Map<Integer, JwtTokenTypeEnum> map = new LinkedHashMap<>();
    static{
        for (JwtTokenTypeEnum item : JwtTokenTypeEnum.values()) {
            map.put(item.getIndex(), item);
        }
    }

    JwtTokenTypeEnum(Integer index, String name) {
        this.name = name;
        this.index = index;
    }

    public static Optional<JwtTokenTypeEnum> of(Integer index) {
        return Optional.ofNullable(map.get(index));
    }

    public static boolean contains(Integer index) {
        return map.get(index) != null;
    }

    public static Optional<String> getName(Integer index) {
        JwtTokenTypeEnum e = map.get(index);
        return e == null ?  Optional.empty() : Optional.of(e.name);
    }

    public static List<JwtTokenTypeEnum> getElementList(){
        return new ArrayList<>(map.values());
    }

    public static List<Integer> getIndexList(){
        return map.values().stream().map(JwtTokenTypeEnum::getIndex).collect(Collectors.toList());
    }

    public static List<String> getNameList(){
        return map.values().stream().map(JwtTokenTypeEnum::getName).collect(Collectors.toList());
    }
}
