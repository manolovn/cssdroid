package com.manolovn.cssdroid;

import com.manolovn.cssdroid.config.CSSDroidExtension;
import com.manolovn.cssdroid.task.ParseCSSTask;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * CSSDroid base plugin
 */
public class CSSDroidPlugin implements Plugin<Project> {

    private static final String EXTENSION = "cssdroid";
    private static final String TASK = "CSSDROIDGENERATE";

    @Override
    public void apply(Project project) {
        project.getExtensions().create(EXTENSION, CSSDroidExtension.class);
        project.getTasks().create(TASK, ParseCSSTask.class);
    }
}
