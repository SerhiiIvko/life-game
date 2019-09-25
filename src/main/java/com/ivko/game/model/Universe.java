package com.ivko.game.model;

public class Universe {
    private char[][] cellHabitat;
    private int height;
    private int width;
    private int generations;

    public Universe(char[][] cellHabitat, int height, int width, int generations) {
        this.cellHabitat = cellHabitat;
        this.height = height;
        this.width = width;
        this.generations = generations;
    }

    public char[][] getCellHabitat() {
        return cellHabitat;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getGenerations() {
        return generations;
    }
}
