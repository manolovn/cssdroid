package com.manolovn.cssdroid.parser.processor;

import com.manolovn.cssdroid.parser.domain.FunctionNode;

/**
 * Processor behavior
 */
public interface Processor {

    String eval(FunctionNode node);
}
