package com.manolovn.cssdroid.parser.processor;

import com.manolovn.cssdroid.parser.domain.FunctionNode;
import com.manolovn.cssdroid.parser.domain.Node;

import java.awt.Color;
import java.util.List;

/**
 * Opacity function processor
 */
public class Opacity implements Processor {

    @Override
    public String eval(FunctionNode node) {
        List<Node> children = node.children();
        Color color = Color.decode(children.get(0).getValue());
        int alpha = Integer.parseInt(children.get(1).getValue());
        return String.format("#%02x%02x%02x%02x",
                alpha,
                color.getRed(),
                color.getGreen(),
                color.getBlue());
    }
}
