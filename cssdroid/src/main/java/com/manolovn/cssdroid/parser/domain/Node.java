package com.manolovn.cssdroid.parser.domain;

import com.manolovn.cssdroid.parser.visitor.NodeVisitor;

import java.util.List;

/**
 * Node entity
 */
public interface Node {

    String getName();

    String getValue();

    void addChild(Node node);

    List<Node> children();

    void accept(NodeVisitor visitor);

}
