package com.manolovn.cssdroid.parser.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Function node
 */
public class FunctionNode implements Node {

    private final FunctionType functionType;
    private List<Node> arguments;

    public FunctionNode(FunctionType functionType) {
        this.functionType = functionType;
        this.arguments = new ArrayList<>();
    }

    @Override
    public String getName() {
        return functionType.name();
    }

    @Override
    public String getValue() {
        return arguments.toString();
    }

    @Override
    public void addChild(Node node) {
        arguments.add(node);
    }

    @Override
    public Collection<Node> children() {
        return arguments;
    }

    @Override
    public String toString() {
        return "FunctionNode{" +
                "functionType=" + functionType +
                ", arguments=" + arguments.toString() +
                '}';
    }
}
