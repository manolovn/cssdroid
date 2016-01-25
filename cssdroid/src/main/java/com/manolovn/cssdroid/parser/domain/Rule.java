package com.manolovn.cssdroid.parser.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Rule entity
 */
public class Rule implements Node {

    private Selector selector;
    private Collection<Property> properties = new ArrayList<>();

    public Rule() {
    }

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public Rule addProperty(Property property) {
        properties.add(property);
        return this;
    }

    public Collection<Property> getProperties() {
        return properties;
    }
}
