package com.manolovn.cssdroid.parser.domain;

import com.manolovn.cssdroid.parser.visitor.NodeVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Function node
 */
public class FunctionNode implements Node {

    private final FunctionType functionType;

    private List<Node> arguments;
    private String value;

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
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void addChild(Node node) {
        arguments.add(node);
    }

    @Override
    public List<Node> children() {
        return arguments;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
        for (Node child : children()) {
            child.accept(visitor);
        }
    }

    @Override
    public String toString() {
        return "FunctionNode{" +
                "functionType=" + functionType +
                ", arguments=" + arguments.toString() +
                '}';
    }
}
