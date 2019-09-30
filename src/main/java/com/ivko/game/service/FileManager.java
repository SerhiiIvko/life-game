package com.ivko.game.service;

import com.ivko.game.properties.AppProperties;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileManager {
    private AppProperties appProperties;

    public FileManager(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public InitialData loadData() throws IOException, URISyntaxException {
        Path path = Paths.get(appProperties.getInputFileName());
        List<String> lines = Files.lines(path).collect(Collectors.toList());
        int height = extractFromPattern(lines.get(0), Pattern.compile(appProperties.getHEIGHT_PATTERN()));
        int width = extractFromPattern(lines.get(1), Pattern.compile(appProperties.getWIDTH_PATTERN()));
        int iterations = extractFromPattern(lines.get(2), Pattern.compile(appProperties.getITERATIONS_PATTERN()));
        if (height < 0 || width < 0 || iterations < 1) {
            return null;
        }
        boolean[][] result = new boolean[width][height];
        for (int i = 3; i < lines.size(); ++i) {
            char[] chars = lines.get(i).toCharArray();
            int rowCounter = 0;
            for (char aChar : chars) {
                if (aChar == appProperties.getALIVE()) {
                    result[i - 3][rowCounter] = true;
                    rowCounter++;
                }
                if (aChar == appProperties.getNON_ALIVE()) {
                    result[i - 3][rowCounter] = false;
                    rowCounter++;
                }
            }
        }
        return new InitialData(result, iterations);
    }

    public void saveResult(boolean[][] gameResult) {
        try {
            Path path = Paths.get(appProperties.getOutputFileName());
            Files.deleteIfExists(path);
            for (boolean[] booleans : gameResult) {
                for (boolean aBoolean : booleans) {
                    String cell = (aBoolean ? appProperties.getALIVE() : appProperties.getNON_ALIVE()) + " ";
                    Files.write(
                            path,
                            cell.getBytes(),
                            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                }
                Files.write(
                        path,
                        "\n".getBytes(),
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int extractFromPattern(String data, Pattern pattern) {
        Matcher heightMatcher = pattern.matcher(data);
        if (heightMatcher.find()) {
            return Integer.parseInt(heightMatcher.group(1));
        }
        throw new IllegalArgumentException("Illegal data: " + data);
    }
}
