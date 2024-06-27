/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.data.mysql.enums;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public enum ProductStockTypeEnum {
    _outOfStock(0, "Out Of Stock"),
    _inStock(1, "In Stock"),
    _preOrder(2, "Pre Order"),
    _backOrder(3, "Back Order");

    @Getter
    private final String name;
    @Getter
    private final Integer index;
    @Getter
    private static final Map<Integer, ProductStockTypeEnum> map = new LinkedHashMap<>();

    static {
        for (ProductStockTypeEnum item : ProductStockTypeEnum.values()) {
            map.put(item.getIndex(), item);
        }
    }

    ProductStockTypeEnum(Integer index, String name) {
        this.name = name;
        this.index = index;
    }

    public static Optional<ProductStockTypeEnum> of(Integer index) {
        return Optional.ofNullable(map.get(index));
    }

    public static boolean contains(Integer index) {
        return map.get(index) != null;
    }

    public static Optional<String> getName(Integer index) {
        ProductStockTypeEnum e = map.get(index);
        return e == null ? Optional.empty() : Optional.of(e.name);
    }

    public static List<ProductStockTypeEnum> getElementList() {
        return new ArrayList<>(map.values());
    }

    public static List<Integer> getIndexList() {
        return map.values().stream().map(ProductStockTypeEnum::getIndex).collect(Collectors.toList());
    }

    public static List<String> getNameList() {
        return map.values().stream().map(ProductStockTypeEnum::getName).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name;
    }
}
