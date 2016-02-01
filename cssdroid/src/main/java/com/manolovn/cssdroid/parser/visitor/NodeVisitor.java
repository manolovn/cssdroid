package com.manolovn.cssdroid.parser.visitor;

import com.manolovn.cssdroid.parser.domain.FunctionNode;
import com.manolovn.cssdroid.parser.domain.PropertyNode;
import com.manolovn.cssdroid.parser.domain.SelectorNode;
import com.manolovn.cssdroid.parser.domain.ValueNode;
import com.manolovn.cssdroid.parser.domain.VariableNode;

/**
 * Node visitor
 */
public interface NodeVisitor {

    void visit(FunctionNode node);

    void visit(PropertyNode node);

    void visit(SelectorNode node);

    void visit(ValueNode node);

    void visit(VariableNode node);

}
