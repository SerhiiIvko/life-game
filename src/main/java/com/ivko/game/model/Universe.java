package com.ivko.game.model;

import com.ivko.game.service.InitialData;

public class Universe {
    private Cell[][] currentGeneration;
    private Cell[][] nextGeneration;
    private int iterations;
    private int width;
    private int height;

    public void init(InitialData initialData) {
        boolean[][] startArray = initialData.getInitGeneration();
        iterations = initialData.getIterations();
        width = startArray.length;
        nextGeneration = new Cell[width][];
        for (int i = 0; i < width; ++i) {
            height = startArray[i].length;
            nextGeneration[i] = new Cell[height];
            for (int j = 0; j < height; ++j) {
                nextGeneration[i][j] = new Cell(startArray[i][j]);
            }
        }
    }

    public void start() {
        while (shouldContinue()) {
            predictNeighbours();
            step();
            iterations--;
        }
    }

    public boolean[][] getFinalData() {
        boolean[][] finalData = new boolean[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; ++j) {
                finalData[i][j] = currentGeneration[i][j].isAlive();
            }
        }
        return finalData;
    }

    private boolean shouldContinue() {
        boolean shouldContinue;
        if (iterations == 0) {
            shouldContinue = false;
        } else {
            boolean wasChanges = false;
            if (currentGeneration != null && nextGeneration != null) {
                all:
                for (int i = 0; i < width; ++i) {
                    for (int j = 0; j < height; ++j) {
                        if (currentGeneration[i][j].isAlive != nextGeneration[i][j].isAlive) {
                            wasChanges = true;
                            break all;
                        }
                    }
                }
            }
            shouldContinue = wasChanges || currentGeneration == null;
        }
        return shouldContinue;
    }

    private void step() {
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                if (!nextGeneration[x][y].isAlive && nextGeneration[x][y].neighbourCounter == 3) {
                    nextGeneration[x][y].isAlive = true;
                } else if (nextGeneration[x][y].isAlive
                        && (nextGeneration[x][y].neighbourCounter < 2 || nextGeneration[x][y].neighbourCounter > 3)) {
                    nextGeneration[x][y].isAlive = false;
                }
            }
        }
    }

    private void predictNeighbours() {
        currentGeneration = nextGeneration;
        nextGeneration = cloneCurrent();
        for (int i = 0; i < currentGeneration.length; ++i) {
            for (int j = 0; j < currentGeneration[i].length; ++j) {
                System.out.print(currentGeneration[i][j].isAlive ? " X" : " .");
                if (currentGeneration[i][j].isAlive) {
                    predictNeighbours(i, j);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private Cell[][] cloneCurrent() {
        Cell[][] clone = new Cell[width][height];
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                clone[i][j] = new Cell(currentGeneration[i][j].isAlive);
            }
        }
        return clone;
    }

    private void predictNeighbours(int i, int j) {
        for (Neighbour neighbour : Neighbour.NEIGHBOURS) {
            int x = neighbour.x + i;
            if (x >= 0 && x < width) {
                int y = neighbour.y + j;
                if (y >= 0 && y < height) {
                    nextGeneration[x][y].neighbourCounter++;
                }
            }
        }
    }

    private static class Cell {
        boolean isAlive;
        int neighbourCounter = 0;

        Cell(boolean isAlive) {
            this.isAlive = isAlive;
        }

        boolean isAlive() {
            return isAlive;
        }
    }

    private static class Neighbour {
        static final Neighbour N = new Neighbour(0, -1);
        static final Neighbour E = new Neighbour(1, 0);
        static final Neighbour S = new Neighbour(0, 1);
        static final Neighbour W = new Neighbour(-1, 0);
        static final Neighbour NW = new Neighbour(-1, -1);
        static final Neighbour NE = new Neighbour(1, -1);
        static final Neighbour SW = new Neighbour(-1, 1);
        static final Neighbour SE = new Neighbour(1, 1);
        static final Neighbour[] NEIGHBOURS = {NW, N, NE, E, SE, S, SW, W};
        int x;
        int y;

        Neighbour(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
