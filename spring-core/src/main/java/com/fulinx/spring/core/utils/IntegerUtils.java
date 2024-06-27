/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class IntegerUtils {

    public static Integer get(String value) {
        return !StringUtils.isNumeric(value) ? 0 : Integer.parseInt(value);
    }

    public static Integer get(Integer value) {

        return value == null ? 0 : value;
    }

    public static Integer get(Double value) {

        return value == null ? 0 : value.intValue();
    }

    private static Integer add(Integer x, Integer y) {

        return get(x) + (get(y));
    }

    private static Integer sub(Integer x, Integer y) {

        return get(x) - (get(y));
    }

    private static Integer multiply(Integer x, Integer y) {

        return get(x) * (get(y));
    }

    public static Integer divide(Integer x, Integer y) {

        return get(x) / (get(y));
    }

    public static Integer add(Integer... values) {
        Integer result = get(values[0]);
        for (int i = 1; i < values.length; i++) {
            Integer value = values[i];
            result = add(result, value);
        }
        return result;
    }

    public static Integer sub(Integer... values) {
        Integer result = get(values[0]);
        for (int i = 1; i < values.length; i++) {
            Integer value = values[i];
            result = sub(result, value);
        }
        return result;
    }

    public static Integer multiply(Integer... values) {
        Integer result = get(values[0]);
        for (int i = 1; i < values.length; i++) {
            Integer value = values[i];
            result = multiply(result, value);
        }
        return result;
    }

    public static int compare(Integer x, Integer y) {
        return get(x).compareTo(get(y));
    }

    public static boolean equals(Integer value1, Integer... values) {
        if (value1 == null) {
            return false;
        }
        Set<Integer> integerSet = new HashSet<>(Arrays.asList(values));
        int size = integerSet.size();
        if (size == 0 || size > 1) {
            return false;
        }
        Integer value2 = integerSet.iterator().next();
        return value1.intValue() == value2.intValue();
    }

    public static Integer defaultIfNull(Integer value, Integer defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static Integer defaultIfNullOrZero(Integer value, Integer defaultValue) {
        return get(value) == 0 ? defaultValue : value;
    }
}

