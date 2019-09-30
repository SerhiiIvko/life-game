package com.ivko.game.core;

import com.ivko.game.model.Universe;
import com.ivko.game.properties.AppProperties;
import com.ivko.game.service.FileManager;
import com.ivko.game.service.InitialData;

public class Game {
    private AppProperties appProperties;
    private FileManager fileManager;

    Game(String inputFileName, String outputFileName) {
        appProperties = new AppProperties(inputFileName, outputFileName);
        fileManager = new FileManager(appProperties);
        start();
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println(AppProperties.WARNING_MESSAGE);
        }
        Game game = new Game(args[0], args[1]);
        game.start();
    }

    private void start() {
        try {
            InitialData initData = fileManager.loadData();
            Universe universe = new Universe();
            universe.init(initData);
            universe.start();
            boolean[][] finalData = universe.getFinalData();
            fileManager.saveResult(finalData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
