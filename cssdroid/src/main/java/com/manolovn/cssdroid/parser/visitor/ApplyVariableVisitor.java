package com.manolovn.cssdroid.parser.visitor;

import com.manolovn.cssdroid.parser.domain.FunctionNode;
import com.manolovn.cssdroid.parser.domain.PropertyNode;
import com.manolovn.cssdroid.parser.domain.SelectorNode;
import com.manolovn.cssdroid.parser.domain.ValueNode;
import com.manolovn.cssdroid.parser.domain.VariableNode;

/**
 * Visitor to apply variables
 */
public class ApplyVariableVisitor implements NodeVisitor {

    private final String variable;
    private final String value;

    public ApplyVariableVisitor(String variable, String value) {
        this.variable = variable;
        this.value = value;
    }

    @Override
    public void visit(FunctionNode node) {

    }

    @Override
    public void visit(PropertyNode node) {

    }

    @Override
    public void visit(SelectorNode node) {

    }

    @Override
    public void visit(ValueNode node) {
        if (node.getValue().equals(variable)) {
            node.setValue(value);
        }
    }

    @Override
    public void visit(VariableNode node) {

    }
}
