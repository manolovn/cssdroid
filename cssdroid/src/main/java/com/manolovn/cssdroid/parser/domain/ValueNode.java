package com.manolovn.cssdroid.parser.domain;

import com.manolovn.cssdroid.parser.visitor.NodeVisitor;

import java.util.Collections;
import java.util.List;

/**
 * Value node
 */
public class ValueNode implements Node {

    private String value;

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
    public List<Node> children() {
        return Collections.emptyList();
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "val: " + value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
