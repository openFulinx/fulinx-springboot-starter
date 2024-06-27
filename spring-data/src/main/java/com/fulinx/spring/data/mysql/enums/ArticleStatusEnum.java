/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.enums;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public enum ArticleStatusEnum {
    _审核通过(1, "审核通过"),
    _正在审核(2, "正在审核"),
    _审核拒绝(3, "审核拒绝"),
    _强制删除(4, "强制删除");

    @Getter
    private final String name;
    @Getter
    private final Integer index;
    @Getter
    private static final Map<Integer, ArticleStatusEnum> map = new LinkedHashMap<>();

    static {
        for (ArticleStatusEnum item : ArticleStatusEnum.values()) {
            map.put(item.getIndex(), item);
        }
    }

    ArticleStatusEnum(Integer index, String name) {
        this.name = name;
        this.index = index;
    }

    public static Optional<ArticleStatusEnum> of(Integer index) {
        return Optional.ofNullable(map.get(index));
    }

    public static boolean contains(Integer index) {
        return map.get(index) != null;
    }

    public static Optional<String> getName(Integer index) {
        ArticleStatusEnum e = map.get(index);
        return e == null ? Optional.empty() : Optional.of(e.name);
    }

    public static List<ArticleStatusEnum> getElementList() {
        return new ArrayList<>(map.values());
    }

    public static List<Integer> getIndexList() {
        return map.values().stream().map(ArticleStatusEnum::getIndex).collect(Collectors.toList());
    }

    public static List<String> getNameList() {
        return map.values().stream().map(ArticleStatusEnum::getName).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name;
    }
}
