package com.ivko.game.service;

import com.ivko.game.properties.AppProperties;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {
//    public void readGameConditions() {
//        FileManagerImpl fileManager = new FileManagerImpl();
//        String gameConditions = fileManager.loadData();
//        if (gameConditions != null && !gameConditions.trim().isEmpty()) {
//            //parse data
//        }
//    }

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

    public String loadData() {
        String conditions = null;
        try {
            conditions = new String(Files.readAllBytes(
                    Paths.get(AppProperties.INPUT_FILE_PATH)),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.getMessage();
        }
        return conditions;
    }
}
