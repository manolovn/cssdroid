package com.manolovn.cssdroid.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

/**
 * Parse CSS task
 */
public class ParseCSSTask extends DefaultTask {

    @TaskAction
    public void doHelloWorld() {
        System.out.println("Hello World!");
    }
}
