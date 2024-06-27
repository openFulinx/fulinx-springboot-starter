package com.fulinx.spring.core.utils;

import com.github.slugify.Slugify;

public class SlugUtils {

    public static String generateSlug(String input) {
        if (input == null) {
            return "";
        }
        Slugify slg = Slugify.builder().build();
        return slg.slugify(input);
    }

}
