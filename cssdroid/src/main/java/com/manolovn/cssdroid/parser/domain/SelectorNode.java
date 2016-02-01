package com.manolovn.cssdroid.parser.domain;

import com.manolovn.cssdroid.parser.visitor.NodeVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * SelectorNode entity
 */
public class SelectorNode implements Node {

    private final String name;
    private final List<Node> properties;

    public SelectorNode(String name) {
        this.name = name;
        this.properties = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return properties.toString();
    }

    @Override
    public void addChild(Node node) {
        if (node == null) {
            return;
        }
        properties.add(node);
    }

    @Override
    public List<Node> children() {
        return properties;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        for (Node node : children()) {
            node.accept(visitor);
        }
    }

    @Override
    public String toString() {
        return "name: " + name + " children: " + properties;
    }
}
