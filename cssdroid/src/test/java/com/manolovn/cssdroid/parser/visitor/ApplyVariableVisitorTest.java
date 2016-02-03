package com.manolovn.cssdroid.parser.visitor;

import com.manolovn.cssdroid.parser.domain.FunctionNode;
import com.manolovn.cssdroid.parser.domain.FunctionType;
import com.manolovn.cssdroid.parser.domain.ValueNode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test for {@link ApplyVariableVisitor}
 */
public class ApplyVariableVisitorTest {

    public static final String ANY_VARIABLE = "@variable";
    public static final String ANY_VARIABLE_VALUE = "12dp";
    public static final String ANY_VARIABLE_NOT_REGISTERED = "@not_registered";

    private ValueNode valueNode;
    private FunctionNode functionNode;
    private ApplyVariableVisitor visitor;

    @Test
    public void shouldApplyVariableToValueNodeWithVariable() {
        givenAVisitor();
        givenAValueNodeWithVariableValue();

        whenVisitValueNode();

        thenNodeHasVariableValue();
    }

    @Test
    public void shouldNotApplyVariableToValueNodeWithVariable() {
        givenAVisitor();
        givenAValueNodeWithNotRegisteredVariableValue();

        whenVisitValueNode();

        thenNodeHasNotVariableValue();
    }

    @Test
    public void shouldNotApplyToFunctionNode() {
        givenAVisitor();
        givenAFunctionNode();

        whenVisitFunctionNode();

        thenFunctionNodeHasNotVariableValue();
    }

    private void givenAFunctionNode() {
        functionNode = new FunctionNode(FunctionType.OPACITY);
    }

    private void givenAValueNodeWithNotRegisteredVariableValue() {
        valueNode = new ValueNode(ANY_VARIABLE_NOT_REGISTERED);
    }

    private void givenAVisitor() {
        visitor = new ApplyVariableVisitor(ANY_VARIABLE, ANY_VARIABLE_VALUE);
    }

    private void givenAValueNodeWithVariableValue() {
        valueNode = new ValueNode(ANY_VARIABLE);
    }

    private void whenVisitValueNode() {
        visitor.visit(valueNode);
    }

    private void whenVisitFunctionNode() {
        visitor.visit(functionNode);
    }

    private void thenNodeHasVariableValue() {
        assertEquals(ANY_VARIABLE_VALUE, valueNode.getValue());
    }

    private void thenNodeHasNotVariableValue() {
        assertNotEquals(ANY_VARIABLE_VALUE, valueNode.getValue());
    }

    private void thenFunctionNodeHasNotVariableValue() {
        assertNotEquals(ANY_VARIABLE_VALUE, functionNode.getValue());
    }
}
