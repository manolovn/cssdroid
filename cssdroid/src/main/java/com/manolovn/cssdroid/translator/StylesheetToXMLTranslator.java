package com.manolovn.cssdroid.translator;

import com.manolovn.cssdroid.parser.domain.Node;
import com.manolovn.cssdroid.parser.domain.StyleSheet;

/**
 * CSS Stylesheet to XML translator
 */
public class StylesheetToXMLTranslator {

    private final AndroidStyleWriter writer;

    public StylesheetToXMLTranslator() {
        writer = new AndroidStyleWriter();
    }

    public String translate(StyleSheet styleSheet) {
        String output = writer.start();
        output += writer.newLine();
        for (Node node : styleSheet.getRules()) {
            output += writer.openStyle(node.getName());
            for(Node child : node.children()) {
                output += writer.addProperty(child.getName(), child.getValue());
            }
            output += writer.closeStyle();
            output += writer.newLine();
        }
        return writer.end(output);
    }
}
