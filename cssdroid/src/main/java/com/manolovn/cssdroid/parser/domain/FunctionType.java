package com.manolovn.cssdroid.parser.domain;

/**
 * Allowed functions
 */
public enum FunctionType {

    OPACITY("opacity");

    private String functionName;

    FunctionType(String functionName) {
        this.functionName = functionName;
    }

    public String getName() {
        return functionName;
    }

    public static FunctionType fromString(String text) {
        if (text != null) {
            for (FunctionType functionType : FunctionType.values()) {
                if (text.equalsIgnoreCase(functionType.functionName)) {
                    return functionType;
                }
            }
        }
        return null;
    }
}
