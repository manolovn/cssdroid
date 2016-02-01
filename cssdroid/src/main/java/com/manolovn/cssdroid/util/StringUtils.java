package com.manolovn.cssdroid.util;

import com.manolovn.cssdroid.parser.domain.FunctionType;

/**
 * StringUtils
 */
public class StringUtils {

    private StringUtils() {
    }

    public static String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    public static String join(FunctionType[] objectArray, String conjunction) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (FunctionType item : objectArray) {
            if (first) {
                first = false;
            } else {
                sb.append(conjunction);
            }
            sb.append(item.getName());
        }
        return sb.toString();
    }
}
