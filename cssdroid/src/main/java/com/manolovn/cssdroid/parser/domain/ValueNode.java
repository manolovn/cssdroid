package com.manolovn.cssdroid.parser.domain;

import java.util.Collection;
import java.util.Collections;

/**
 * Value node
 */
public class ValueNode implements Node {

    private final String value;

    public ValueNode(String value) {
        this.value = value;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void addChild(Node node) {

    }

    @Override
    public Collection<Node> children() {
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return "val: " + value;
    }
}
