package com.manolovn.cssdroid.parser;

import org.junit.Before;
import org.junit.Test;

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
    public void shouldDoNothig() {
        parser.parseTokens("");
    }

    @Test
    public void shouldExtractVariableNode() {
        parser.parseTokens("@variable: 12sp;");
        parser.parseTokens("@variable: 12dp;");
        parser.parseTokens("@variable: 12dip;");
        parser.parseTokens("@variable: #333333;");
        parser.parseTokens("@variable: #000;");
    }

    @Test
    public void shouldExtractSelector() {
        parser.parseTokens(".empty {}");
    }

    @Test
    public void shouldExtractProperties() {
        parser.parseTokens(".header {\n" +
                "    textColor: #111222;\n" +
                "    layout-marginRight: 12dp;\n" +
                "}");
    }

    @Test
    public void shouldExtractFunction() {
        parser.parseTokens(".h3 {\n" +
                "    background: opacity(#333, 100);\n" +
                "    textSize: 18sp;\n" +
                "}");
    }
}
