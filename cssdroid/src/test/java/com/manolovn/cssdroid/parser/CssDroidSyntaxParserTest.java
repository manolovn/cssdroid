package com.manolovn.cssdroid.parser;

import com.manolovn.cssdroid.parser.domain.FunctionNode;
import com.manolovn.cssdroid.parser.domain.Node;
import com.manolovn.cssdroid.parser.domain.PropertyNode;
import com.manolovn.cssdroid.parser.domain.SelectorNode;
import com.manolovn.cssdroid.parser.domain.StyleSheet;
import com.manolovn.cssdroid.parser.domain.ValueNode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for {@link CssDroidSyntaxParser}
 */
public class CssDroidSyntaxParserTest {

    private CssDroidSyntaxParser parser;

    @Before
    public void setUp() {
        parser = new CssDroidSyntaxParser();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionOnParseNothing() {
        parser.parseTokens("");
    }

    @Test
    public void shouldExtractVariable() {
        StyleSheet styleSheet = parser.parseTokens("@variable: 12sp;");

        Node node = styleSheet.getVariables().iterator().next();

        assertEquals(styleSheet.getVariables().size(), 1);
        assertTrue(node.getName().equalsIgnoreCase("@variable"));
    }

    @Test
    public void shouldExtractOneRule() {
        StyleSheet styleSheet = parser.parseTokens(".empty {}");

        assertEquals(styleSheet.getRules().size(), 1);
    }

    @Test
    public void shouldExtractOneRuleWihtOneChild() {
        StyleSheet styleSheet = parser.parseTokens(".empty {"
                + "textColor: #444;"
                + "}");

        assertEquals(styleSheet.getRules().size(), 1);
        assertEquals(styleSheet.getRules().iterator().next().children().size(), 1);
    }

    @Test
    public void shouldExtractSelector() {
        StyleSheet styleSheet = parser.parseTokens(".empty {}");

        Node node = styleSheet.getRules().iterator().next();

        assertEquals(styleSheet.getRules().size(), 1);
        assertTrue(node instanceof SelectorNode);
        assertTrue(node.getName().equalsIgnoreCase(".empty"));
    }

    @Test
    public void shouldExtractFunction() {
        StyleSheet styleSheet = parser.parseTokens(".sample {"
                + "    background: opacity(#333333, 100);"
                + "}");

        Node selectorNode = styleSheet.getRules().iterator().next();

        assertTrue(selectorNode instanceof SelectorNode);
        assertTrue(selectorNode.getName().equalsIgnoreCase(".sample"));

        Node propertyNode = selectorNode.children().get(0);
        assertTrue(propertyNode instanceof PropertyNode);
        assertTrue(propertyNode.getName().equalsIgnoreCase("background"));

        Node functionNode = propertyNode.children().get(0);
        assertTrue(functionNode instanceof FunctionNode);
        assertTrue(functionNode.getName().equalsIgnoreCase("opacity"));

        Node colorNode = functionNode.children().get(0);
        assertTrue(colorNode instanceof ValueNode);
        assertTrue(colorNode.getValue().equalsIgnoreCase("#333333"));

        Node numberNode = functionNode.children().get(1);
        assertTrue(numberNode instanceof ValueNode);
        assertTrue(numberNode.getValue().equalsIgnoreCase("100"));
    }
}
