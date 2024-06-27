/*
 * Copyright (c) Minong Tech. 2023.
 */

package com.fulinx.spring.core.utils;

import java.util.Random;

public class ToolUtils {

    /**
     * Generate random string
     *
     * @param length int length
     * @return random string
     */
    public static String generateRandomString(int length) {
        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
