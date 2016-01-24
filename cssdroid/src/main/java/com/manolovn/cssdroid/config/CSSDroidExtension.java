package com.manolovn.cssdroid.config;

/**
 * CSSDroid configuration extension
 */
public class CSSDroidExtension {

    private String cssDir = "/src/main/acss/";
    private String outputDir = "/src/main/res/values/";

    public String getCssDir() {
        return cssDir;
    }

    public void setCssDir(String cssDir) {
        this.cssDir = cssDir;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }
}
