package com.ivko.game.core;

import com.ivko.game.model.Universe;
import com.ivko.game.service.FileManager;

public class Game {

    public static void main(String[] args) {
        doSimulation();
    }

    private static void doSimulation() {
        FileManager parser = new FileManager();
        boolean[][] initData = parser.loadData();
        Universe universe = new Universe();
        universe.init(initData);
        universe.start();
    }
}
