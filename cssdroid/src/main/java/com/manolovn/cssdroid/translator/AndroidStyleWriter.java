package com.manolovn.cssdroid.translator;

import com.manolovn.cssdroid.util.StringUtils;

/**
 * Android styles writer
 */
public class AndroidStyleWriter {

    public String start() {
        return "<resources>\n";
    }

    public String end(String input) {
        return input + "</resources>\n";
    }

    public String newLine() {
        return "\n";
    }

    public String openStyle(String style) {
        return "    <style name=\"" + StringUtils.capitalize(style.replace(".", "")) + "\">\n";
    }

    public String closeStyle() {
        return "    </style>\n";
    }

    public String addProperty(String name, String value) {
        return "        <item name=\"android:" + name.replace("-", "_") + "\">"
                + value + "</item>\n";
    }
}
