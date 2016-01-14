package com.manolovn.cssdroid;

import com.manolovn.cssdroid.task.ParseCSSTask;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * CSSDroid base plugin
 */
public class CSSDroidPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getTasks().create("cssdroidGenerate", ParseCSSTask.class);
    }
}
