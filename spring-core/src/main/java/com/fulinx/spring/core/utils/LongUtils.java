/*
 * Copyright (c) Minong Tech. 2016-2023.
 */

package com.fulinx.spring.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongUtils {

  public static Long get(String value) {

    return !StringUtils.isNumeric(value) ? 0L : Long.parseLong(value);
  }

  public static Long get(Long value) {

    return value == null ? 0L : value;
  }

  public static Long get(Double value) {

    return value == null ? 0L : value.longValue();
  }

  public static Long get(Integer value) {

    return value == null ? 0L : value.longValue();
  }

  public static Long add(Long...values){
    Long result = get(values[0]);
    for(int i = 1; i < values.length; i ++){
      Long value = values[i];
      result = add(result, value);
    }
    return result;
  }

  public static Long sub(Long...values){
    Long result = get(values[0]);
    for(int i = 1; i < values.length; i ++){
      Long value = values[i];
      result = sub(result, value);
    }
    return result;
  }

  public static Long multiply(Long...values){
    Long result = get(values[0]);
    for(int i = 1; i < values.length; i ++){
      Long value = values[i];
      result = multiply(result, value);
    }
    return result;
  }

  public static Long divide(Long x, Long y) {

    return get(x) / (get(y));
  }

  public static int compare(Long x, Long y) {
    return get(x).compareTo(get(y));
  }

  public static boolean equals(Long value1, Long...values) {
    if(value1 == null){
      return false;
    }
    Set<Long> longSet = new HashSet<>(Arrays.asList(values));
    int size = longSet.size();
    if(size == 0 || size > 1){
      return false;
    }
    Long value2 = longSet.iterator().next();
    return value1.longValue() == value2.longValue();
  }
}
