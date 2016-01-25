package com.manolovn.cssdroid.task;

import com.manolovn.cssdroid.config.CSSDroidExtension;
import com.manolovn.cssdroid.parser.CssDroidParser;
import com.manolovn.cssdroid.parser.domain.Rule;
import com.manolovn.cssdroid.translator.RuleToXMLTranslator;
import com.manolovn.cssdroid.util.FileUtils;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collection;

/**
 * Parse CSS task
 */
public class ParseCSSTask extends DefaultTask {

    private CssDroidParser parser;
    private RuleToXMLTranslator translator;
    private CSSDroidExtension configuration;

    @TaskAction
    public void generate() throws IOException {
        parser = new CssDroidParser();
        translator = new RuleToXMLTranslator();

        configuration = getProject().getExtensions().findByType(CSSDroidExtension.class);
        if (configuration == null) {
            configuration = new CSSDroidExtension();
        }

        File folder = new File(getProject().getProjectDir() + configuration.getCssDir());
        listFilesForFolder(folder);
    }

    private void listFilesForFolder(final File folder) throws IOException {
        if (folder == null || folder.listFiles() == null) {
            System.out.println("EMPTY FOLDER!!! " + folder.getPath());
            return;
        }
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                createStyleFile(fileEntry, FileUtils.removeExtension(fileEntry));
                System.out.println(">> " + fileEntry.getName());
            }
        }
    }

    private void createStyleFile(File source, String name) throws IOException {
        File dest = new File(getProject().getProjectDir() + configuration.getOutputDir());
        Collection<Rule> rules = parser.parse(source);
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(dest + "/styles-" + name + ".xml"), "utf-8"))) {
            writer.write(translator.translate(rules));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
