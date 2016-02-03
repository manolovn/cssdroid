package com.manolovn.cssdroid.parser.processor;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test for {@link ProcessorFactory}
 */
public class ProcessorFactoryTest {

    public static final String ANY_VALID_FUNCTION_NAME = "opacity";
    public static final String ANY_INVALID_FUNCTION_NAME = "wrongFunction";

    private Processor processor;

    @Test
    public void shouldGenerateProcessorCorrectly() {
        whenBuildAProcessor();

        thenProcessorGeneratedCorrectly();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenFunctionNameNotFound() {
        whenBuildAProcessorWithAWrongFunctionName();
    }

    private void whenBuildAProcessorWithAWrongFunctionName() {
        processor = ProcessorFactory.getFunctionByName(ANY_INVALID_FUNCTION_NAME);
    }

    private void whenBuildAProcessor() {
        processor = ProcessorFactory.getFunctionByName(ANY_VALID_FUNCTION_NAME);
    }

    private void thenProcessorGeneratedCorrectly() {
        assertTrue(processor instanceof Opacity);
    }
}
