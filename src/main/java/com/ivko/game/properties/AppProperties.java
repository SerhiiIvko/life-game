package com.ivko.game.properties;

public class AppProperties {
    public final static String WARNING_MESSAGE = "Incorrect param arguments.";
    private final char ALIVE = 'X';
    private final char NON_ALIVE = 'O';
    private final String WIDTH_PATTERN = "WIDTH=(\\d{1,10});";
    private final String HEIGHT_PATTERN = "HEIGHT=(\\d{1,10});";
    private final String ITERATIONS_PATTERN = "ITERATIONS=(\\d{1,10});";

    private String inputFileName;
    private String outputFileName;

    public AppProperties(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public char getALIVE() {
        return ALIVE;
    }

    public char getNON_ALIVE() {
        return NON_ALIVE;
    }

    public String getWIDTH_PATTERN() {
        return WIDTH_PATTERN;
    }

    public String getHEIGHT_PATTERN() {
        return HEIGHT_PATTERN;
    }

    public String getITERATIONS_PATTERN() {
        return ITERATIONS_PATTERN;
    }
}
