package com.manolovn.cssdroid.parser.processor;

import com.manolovn.cssdroid.parser.domain.FunctionType;

/**
 * Processor factory
 */
public class ProcessorFactory {

    private ProcessorFactory() {

    }

    public static Processor getFunctionByName(String functionName) {
        if (functionName.toLowerCase().equals(FunctionType.OPACITY.getName())) {
            return new Opacity();
        }
        throw new IllegalArgumentException("Can't find function to eval " + functionName);
    }
}
