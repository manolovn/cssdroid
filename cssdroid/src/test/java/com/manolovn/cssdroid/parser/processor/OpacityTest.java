package com.manolovn.cssdroid.parser.processor;

import com.manolovn.cssdroid.parser.domain.FunctionNode;
import com.manolovn.cssdroid.parser.domain.FunctionType;
import com.manolovn.cssdroid.parser.domain.ValueNode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for {@link Opacity}
 */
public class OpacityTest {

    public static final String ANY_COLOR = "#333333";
    public static final String ANY_ALPHA_VALUE = "50";
    public static final String EXPECTED_COLOR = "#32333333";

    private Opacity opacity;
    private FunctionNode functionNode;
    private String colorGenerated;

    @Test
    public void shouldEvalCorrectlyAFucntionNode() {
        givenAnOpacityProcessor();
        givenAFunctionNode();

        whenEvalFunction();

        thenColorHasTransparencyApplied();
    }

    private void givenAnOpacityProcessor() {
        opacity = new Opacity();
    }

    private void givenAFunctionNode() {
        functionNode = new FunctionNode(FunctionType.OPACITY);
        functionNode.addChild(new ValueNode(ANY_COLOR));
        functionNode.addChild(new ValueNode(ANY_ALPHA_VALUE));
    }

    private void whenEvalFunction() {
        colorGenerated = opacity.eval(functionNode);
    }

    private void thenColorHasTransparencyApplied() {
        assertEquals(EXPECTED_COLOR, colorGenerated);
    }
}
