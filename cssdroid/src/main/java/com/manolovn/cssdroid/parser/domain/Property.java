package com.manolovn.cssdroid.parser.domain;

/**
 * Property entity
 */
public class Property implements Node {

    private final String name;
    private final String value;

    public Property(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
