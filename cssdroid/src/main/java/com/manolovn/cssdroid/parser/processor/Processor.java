package com.manolovn.cssdroid.parser.processor;

import com.manolovn.cssdroid.parser.domain.Property;

/**
 * Processor behavior
 */
public interface Processor {

    String eval(Property value);
}
