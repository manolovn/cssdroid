package com.manolovn.cssdroid.parser.domain;

/**
 * Selector entity
 */
public class Selector implements Node {

    private final String name;

    public Selector(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
