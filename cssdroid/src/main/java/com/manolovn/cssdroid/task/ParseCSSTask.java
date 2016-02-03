package com.manolovn.cssdroid.task;

import com.manolovn.cssdroid.config.CSSDroidExtension;
import com.manolovn.cssdroid.parser.CssDroidSyntaxParser;
import com.manolovn.cssdroid.translator.StylesheetToXMLTranslator;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;

/**
 * Parse CSS task
 */
public class ParseCSSTask extends DefaultTask {

    private CssDroidGenerator generator;
    private CSSDroidExtension configuration;

    @TaskAction
    public void generate() throws IOException {
        initDependencies();
        initConfiguration();

        File projectDir = getProject().getProjectDir();
        File cssDir = new File(projectDir + configuration.getCssDir());
        File outputDir = new File(projectDir + configuration.getOutputDir());

        generator.generate(cssDir, outputDir);
    }

    private void initDependencies() {
        CssDroidSyntaxParser parser = new CssDroidSyntaxParser();
        StylesheetToXMLTranslator translator = new StylesheetToXMLTranslator();
        generator = new CssDroidGenerator(parser, translator);
    }

    private void initConfiguration() {
        configuration = getProject().getExtensions().findByType(CSSDroidExtension.class);
        if (configuration == null) {
            configuration = new CSSDroidExtension();
        }
    }
}
