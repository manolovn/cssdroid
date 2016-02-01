package com.manolovn.cssdroid.parser.domain;

import com.manolovn.cssdroid.parser.visitor.NodeVisitor;

import java.util.Collections;
import java.util.List;

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
    public List<Node> children() {
        return Collections.singletonList(value);
    }

    @Override
    public void accept(NodeVisitor visitor) {
        value.accept(visitor);
    }

    @Override
    public String toString() {
        return "{ " + name + " : " + value + " }";
    }
}
