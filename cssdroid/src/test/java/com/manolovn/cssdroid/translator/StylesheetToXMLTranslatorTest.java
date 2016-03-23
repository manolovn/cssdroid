package com.manolovn.cssdroid.translator;

import com.manolovn.cssdroid.parser.domain.PropertyNode;
import com.manolovn.cssdroid.parser.domain.SelectorNode;
import com.manolovn.cssdroid.parser.domain.StyleSheet;
import com.manolovn.cssdroid.parser.domain.ValueNode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for {@link StylesheetToXMLTranslator}
 */
public class StylesheetToXMLTranslatorTest {

    private StyleSheet styleSheet;
    private String styleGenerated;
    private StylesheetToXMLTranslator translator;

    @Test
    public void shouldGenerateCorrectXmlStyle() {
        givenATranslator();
        givenAnStylesheet();

        whenTranslateStylesheet();

        thenXmlIsGeneratedCorrectly();
    }

    private void givenATranslator() {
        translator = new StylesheetToXMLTranslator();
    }

    private void thenXmlIsGeneratedCorrectly() {
        String expectedStyle = "<resources>\n"
                + "\n"
                + "    <style name=\"Sample\">\n"
                + "        <item name=\"android:textSize\">12sp</item>\n"
                + "    </style>\n"
                + "\n"
                + "</resources>\n";
        assertEquals(expectedStyle.replace(" ", ""), styleGenerated.replace(" ", ""));
    }

    private void whenTranslateStylesheet() {
        styleGenerated = translator.translate(styleSheet);
    }

    private void givenAnStylesheet() {
        SelectorNode selectorNode = new SelectorNode(".sample");
        selectorNode.addChild(new PropertyNode("textSize", new ValueNode("12sp")));

        styleSheet = new StyleSheet();
        styleSheet.addRule(selectorNode);
    }
}
