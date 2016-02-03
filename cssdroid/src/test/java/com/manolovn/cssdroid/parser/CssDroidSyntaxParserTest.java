package com.manolovn.cssdroid.parser;

import com.manolovn.cssdroid.parser.domain.Node;
import com.manolovn.cssdroid.parser.domain.PropertyNode;
import com.manolovn.cssdroid.parser.domain.SelectorNode;
import com.manolovn.cssdroid.parser.domain.StyleSheet;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
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
    public void shouldExtractOneVariable() {
        StyleSheet styleSheet = parser.parseTokens("@variable: 12sp;");

        assertEquals(styleSheet.getVariables().size(), 1);
    }

    @Test
    public void shouldExtractOneRule() {
        StyleSheet styleSheet = parser.parseTokens(".empty {}");

        assertEquals(styleSheet.getRules().size(), 1);
    }

    @Test
    public void shouldExtractOneRuleWihtOneChild() {
        StyleSheet styleSheet = parser.parseTokens(".empty {" +
                "textColor: #444;" +
                "}");

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
}
