package com.manolovn.cssdroid.parser.visitor;

import com.manolovn.cssdroid.parser.domain.FunctionNode;
import com.manolovn.cssdroid.parser.domain.PropertyNode;
import com.manolovn.cssdroid.parser.domain.SelectorNode;
import com.manolovn.cssdroid.parser.domain.ValueNode;
import com.manolovn.cssdroid.parser.domain.VariableNode;
import com.manolovn.cssdroid.parser.processor.Processor;
import com.manolovn.cssdroid.parser.processor.ProcessorFactory;

/**
 * Visitor to eval functions
 */
public class EvalFunctionVisitor implements NodeVisitor {

    @Override
    public void visit(FunctionNode node) {
        Processor processor = ProcessorFactory.getFunctionByName(node.getName());
        node.setValue(processor.eval(node));
    }

    @Override
    public void visit(PropertyNode node) {

    }

    @Override
    public void visit(SelectorNode node) {

    }

    @Override
    public void visit(ValueNode node) {

    }

    @Override
    public void visit(VariableNode node) {

    }
}
