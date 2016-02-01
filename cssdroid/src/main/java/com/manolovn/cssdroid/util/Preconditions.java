package com.manolovn.cssdroid.util;

import org.gradle.api.Nullable;

public class Preconditions {

    public static void checkArgument(boolean expression, @Nullable Object errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }
}
