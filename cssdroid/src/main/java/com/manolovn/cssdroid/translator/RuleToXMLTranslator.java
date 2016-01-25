package com.manolovn.cssdroid.translator;

import com.manolovn.cssdroid.parser.domain.Rule;

import java.util.Collection;

/**
 * CSS Rule to XML translator
 */
public class RuleToXMLTranslator {

    public String translate(Collection<Rule> rules) {
        return "<resources>\n" +
                "\n" +
                "    <style name=\"Sample\">\n" +
                "        <item name=\"android:textColor\">#FF0000</item>\n" +
                "    </style>\n" +
                "\n" +
                "</resources>\n";
    }
}
