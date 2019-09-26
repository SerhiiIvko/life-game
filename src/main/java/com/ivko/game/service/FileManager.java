package com.ivko.game.service;

import com.ivko.game.properties.AppProperties;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileManager {

    public void saveResult(String gameResult) {
        try {
            String path = AppProperties.OUTPUT_FILE_PATH;
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(gameResult);
            fileWriter.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public boolean[][] loadData() {
        boolean[][] result = null;
        try {
            List<List<Boolean>> data = new ArrayList<>();
            Stream<String> lines = Files.lines(Paths.get(AppProperties.INPUT_FILE_PATH));
            List<Boolean> lineData = new ArrayList<>();
            lines.forEach(line ->
            {
                if (!(line.startsWith("HEIGHT")
                        || line.startsWith("WIDTH")
                        || line.startsWith("ITERATIONS"))) {
                    char[] chars = line.toCharArray();
                    for (char ch : chars) {
                        if (ch == 'X') {
                            lineData.add(true);
                        }
                        if (ch == 'O') {
                            lineData.add(false);
                        }
                    }
                    data.add(new ArrayList<>(lineData));
                    lineData.clear();
                }
            });
            lines.close();
            result = new boolean[data.size()][];
            for (int i = 0; i < data.size(); ++i) {
                result[i] = new boolean[data.get(i).size()];
                for (int j = 0; j < data.get(i).size(); ++j) {
                    result[i][j] = data.get(i).get(j);
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
