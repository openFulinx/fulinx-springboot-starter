/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.enums;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public enum ProductSourceTypeEnum {
    _own(1, "Own"),
    _oem(2, "Oem"),
    _virtual(3, "virtual"),
    _other(4, "virtual");

    @Getter
    private final String name;
    @Getter
    private final Integer index;
    @Getter
    private static final Map<Integer, ProductSourceTypeEnum> map = new LinkedHashMap<>();

    static {
        for (ProductSourceTypeEnum item : ProductSourceTypeEnum.values()) {
            map.put(item.getIndex(), item);
        }
    }

    ProductSourceTypeEnum(Integer index, String name) {
        this.name = name;
        this.index = index;
    }

    public static Optional<ProductSourceTypeEnum> of(Integer index) {
        return Optional.ofNullable(map.get(index));
    }

    public static boolean contains(Integer index) {
        return map.get(index) != null;
    }

    public static Optional<String> getName(Integer index) {
        ProductSourceTypeEnum e = map.get(index);
        return e == null ? Optional.empty() : Optional.of(e.name);
    }

    public static List<ProductSourceTypeEnum> getElementList() {
        return new ArrayList<>(map.values());
    }

    public static List<Integer> getIndexList() {
        return map.values().stream().map(ProductSourceTypeEnum::getIndex).collect(Collectors.toList());
    }

    public static List<String> getNameList() {
        return map.values().stream().map(ProductSourceTypeEnum::getName).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name;
    }
}
