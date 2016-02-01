package com.manolovn.cssdroid.parser.domain;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Stylesheet main context
 */
public class StyleSheet {

    private Set<Node> rules;
    private Set<VariableNode> variables;

    public StyleSheet() {
        rules = new LinkedHashSet<>();
        variables = new LinkedHashSet<>();
    }

    public Set<VariableNode> getVariables() {
        return variables;
    }

    public void addVariable(VariableNode node) {
        if (node == null) {
            return;
        }
        variables.add(node);
    }

    public void addRule(Node rule) {
        if (rule == null) {
            return;
        }
        rules.add(rule);
    }

    public Set<Node> getRules() {
        return rules;
    }
}
