package com.manolovn.cssdroid.parser.domain;

import java.util.Collection;
import java.util.Collections;

/**
 * PropertyNode entity
 */
public class PropertyNode implements Node {

    private final String name;
    private final Node value;

    public PropertyNode(String name, Node value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value.getValue();
    }

    @Override
    public void addChild(Node node) {

    }

    @Override
    public Collection<Node> children() {
        return Collections.singletonList(value);
    }

    @Override
    public String toString() {
        return "{ " + name + " : " + value + " }";
    }
}
