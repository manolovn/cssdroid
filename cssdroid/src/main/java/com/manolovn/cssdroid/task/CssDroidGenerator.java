package com.manolovn.cssdroid.task;

import com.manolovn.cssdroid.parser.CssDroidSyntaxParser;
import com.manolovn.cssdroid.parser.domain.StyleSheet;
import com.manolovn.cssdroid.translator.StylesheetToXMLTranslator;
import com.manolovn.cssdroid.util.CssDroidFileUtils;
import com.manolovn.cssdroid.util.Preconditions;

import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Main CSS to android styles generator
 */
class CssDroidGenerator {

    private final CssDroidSyntaxParser parser;
    private final StylesheetToXMLTranslator translator;

    CssDroidGenerator(CssDroidSyntaxParser parser,
                      StylesheetToXMLTranslator translator) {
        this.parser = parser;
        this.translator = translator;
    }

    public void generate(File cssDir, File outputDir) throws IOException {
        traverseCssDirAndGenerateStyles(cssDir, outputDir);
    }

    private void traverseCssDirAndGenerateStyles(File inputFolder, File outputDir)
            throws IOException {
        Preconditions.checkNotNull(inputFolder);
        Preconditions.checkNotNull(inputFolder.listFiles());
        Preconditions.checkArgument(inputFolder.listFiles().length > 0,
                "Empty folder " + inputFolder.getPath());

        for (final File file : inputFolder.listFiles()) {
            if (file.isDirectory()) {
                traverseCssDirAndGenerateStyles(file, outputDir);
            } else {
                createStyleFile(file, outputDir);
            }
        }
    }

    private void createStyleFile(File source, File dest) throws IOException {
        final String name = CssDroidFileUtils.removeExtension(source);
        final String content = FileUtils.readFileToString(source);

        StyleSheet styleSheet = parser.parseTokens(content);

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(dest + "/styles-" + name + ".xml"), "utf-8"))) {
            writer.write(translator.translate(styleSheet));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
