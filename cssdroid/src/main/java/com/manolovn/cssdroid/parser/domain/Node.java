package com.manolovn.cssdroid.parser.domain;

import java.util.Collection;

/**
 * Node entity
 */
public interface Node {

    String getName();

    String getValue();

    void addChild(Node node);

    Collection<Node> children();

}
