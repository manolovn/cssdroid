package com.manolovn.cssdroid.task;

import com.manolovn.cssdroid.config.CSSDroidExtension;
import com.manolovn.cssdroid.parser.CssDroidSyntaxParser;
import com.manolovn.cssdroid.parser.domain.StyleSheet;
import com.manolovn.cssdroid.translator.StylesheetToXMLTranslator;
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

    private CssDroidSyntaxParser parser;
    private StylesheetToXMLTranslator translator;
    private CSSDroidExtension configuration;

    @TaskAction
    public void generate() throws IOException {
        parser = new CssDroidSyntaxParser();
        translator = new StylesheetToXMLTranslator();

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
        final String content = org.apache.commons.io.FileUtils.readFileToString(source);
        StyleSheet styleSheet = parser.parseTokens(content);
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(dest + "/styles-" + name + ".xml"), "utf-8"))) {
            writer.write(translator.translate(styleSheet));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
