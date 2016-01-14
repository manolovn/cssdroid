package com.manolovn.cssdroid.task;

import com.manolovn.cssdroid.config.CSSDroidExtension;
import com.manolovn.cssdroid.parser.CSSParser;
import com.manolovn.cssdroid.util.FileUtils;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Parse CSS task
 */
public class ParseCSSTask extends DefaultTask {

    private CSSParser cssParser;
    private CSSDroidExtension configuration;

    @TaskAction
    public void doHelloWorld() {
        cssParser = new CSSParser();

        CSSDroidExtension extension =
                getProject().getExtensions().findByType(CSSDroidExtension.class);
        configuration = extension;
        if (configuration == null) {
            configuration = new CSSDroidExtension();
        }

        File folder = new File(getProject().getBuildDir() + configuration.getCssDir());
        listFilesForFolder(folder);
    }

    private void listFilesForFolder(final File folder) {
        if (folder == null || folder.listFiles() == null) {
            return;
        }
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                createStyleFile(fileEntry, FileUtils.removeExtension(fileEntry));
                System.out.println(fileEntry.getName());
            }
        }
    }

    private void createStyleFile(File source, String name) {
        File dest = new File(getProject().getBuildDir() + configuration.getOutputDir());
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(dest + "/styles-" + name + ".xml"), "utf-8"))) {
            writer.write(cssParser.parse(source));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
