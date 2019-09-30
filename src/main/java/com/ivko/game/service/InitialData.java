package com.ivko.game.service;

public class InitialData {
    private boolean[][] initGeneration;
    private int iterations;

    InitialData(boolean[][] initGeneration, int iterations) {
        this.initGeneration = initGeneration;
        this.iterations = iterations;
    }

    public boolean[][] getInitGeneration() {
        return initGeneration;
    }

    public int getIterations() {
        return iterations;
    }
}
