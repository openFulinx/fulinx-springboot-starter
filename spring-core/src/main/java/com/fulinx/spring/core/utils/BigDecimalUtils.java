/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtils {


    public static BigDecimal get(String value) {

        return value == null ? BigDecimal.valueOf(0) : new BigDecimal(value);
    }

    public static BigDecimal get(BigDecimal value) {

        return value == null ? BigDecimal.valueOf(0) : value;
    }

    public static BigDecimal get(Integer value) {

        return value == null ? BigDecimal.valueOf(0) : new BigDecimal(value);
    }

    public static BigDecimal get(Double value) {

        return value == null ? BigDecimal.valueOf(0) : new BigDecimal(value);
    }

    public static BigDecimal get(double value) {

        return new BigDecimal(value);
    }

    public static BigDecimal add(BigDecimal x, BigDecimal y) {

        return get(x).add(get(y));
    }

    public static BigDecimal multiply(BigDecimal x, BigDecimal y) {

        return get(x).multiply(get(y));
    }

    private static BigDecimal divide(BigDecimal x, BigDecimal y) {

        if (y == null || y.compareTo(BigDecimal.valueOf(0)) == 0) {

            throw new RuntimeException("divided by zero");
        }

        return get(x).divide(get(y));
    }

    public static BigDecimal divide(BigDecimal x, BigDecimal y, int scale, RoundingMode roundingMode) {

        if (y == null || y.compareTo(BigDecimal.valueOf(0)) == 0) {

            throw new RuntimeException("divided by zero");
        }

        return get(x).divide(get(y), scale, roundingMode);
    }

    public static BigDecimal divide(BigDecimal x, BigDecimal y, int scale, int roundingMode) {

        if (y == null || y.compareTo(BigDecimal.valueOf(0)) == 0) {

            throw new RuntimeException("divided by zero");
        }

        return get(x).divide(get(y), scale, roundingMode);
    }

    public static BigDecimal subtract(BigDecimal x, BigDecimal y) {

        return get(x).subtract(get(y));
    }

    public static boolean equals(BigDecimal x, BigDecimal y) {
        if (x == null || y == null) {
            return false;
        } else {
            return x.compareTo(y) == 0;
        }
    }

    public static int compareTo(BigDecimal x, BigDecimal y){
        return get(x).compareTo(get(y));
    }


    public static BigDecimal add(BigDecimal...values){
        BigDecimal result = get(values[0]);
        for(int i = 1; i < values.length; i ++){
            BigDecimal value = values[i];
            result = add(result, value);
        }
        return result;
    }

    public static BigDecimal subtract(BigDecimal...values){
        BigDecimal result = get(values[0]);
        for(int i = 1; i < values.length; i ++){
            BigDecimal value = values[i];
            result = subtract(result, value);
        }
        return result;
    }
}
