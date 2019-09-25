package com.ivko.game.core;

import com.ivko.game.model.Universe;
import com.ivko.game.service.FileManager;
import com.ivko.game.service.FileParser;

import java.util.Arrays;

public class Game {
    private static FileParser parser = new FileParser();
    private static Universe universe = parser.getUniverseFromFile();

    public static void main(String[] args) {
        doSimulation();
    }

    public static void doSimulation() {
        FileParser parser = new FileParser();
        Universe universe = parser.getUniverseFromFile();
        String[] dish = getResult().split("\\n");
        int gens = universe.getGenerations();
        for (int i = 0; i < gens + 1; i++) {
            System.out.println("Generation " + i + ":");
            print(dish);
            dish = simulateLife();
        }
    }

    public static String[] simulateLife() {
        String[] firstGeneration = getResult().split("\\n");
        String[] nextGeneration = new String[firstGeneration.length];
        for (int row = 0; row < firstGeneration.length; row++) {
            nextGeneration[row] = "";
            for (int i = 0; i < firstGeneration[row].length(); i++) {
                String above;
                String same;
                String below;
                String left = "";
                String right = "";
                if (i == 0) {
                    if (row == 0) {
                        above = null;
                    } else {
                        above = firstGeneration[row - 1].substring(i, i + 2);
                    }
                    same = firstGeneration[row].substring(i + 1, i + 2);
                    if (row == firstGeneration.length - 1) {
                        below = null;
                    } else {
                        below = firstGeneration[row + 1].substring(i, i + 2);
                    }
                } else if (i == firstGeneration[row].length() - 1) {
                    if (row == 0) {
                        above = null;
                    } else {
                        above = firstGeneration[row - 1].substring(i - 1, i + 1);
                    }
                    same = firstGeneration[row].substring(i - 1, i);
                    if (row == firstGeneration.length - 1) {
                        below = null;
                    } else {
                        below = firstGeneration[row + 1].substring(i - 1, i + 1);
                    }
                } else {
                    if (row == 0) {
                        above = null;
                    } else {
                        above = firstGeneration[row - 1].substring(i - 1, i + 2);
                    }
                    same = firstGeneration[row].substring(i - 1, i) + firstGeneration[row].substring(i + 1, i + 2);
                    if (row == firstGeneration.length - 1) {
                        below = null;
                    } else {
                        below = firstGeneration[row + 1].substring(i - 1, i + 2);
                    }
                }
                int neighbors = getNeighbors(above, same, below, left, right);
                if (neighbors < 2 || neighbors > 3) {
                    nextGeneration[row] += " ";
                } else if (neighbors == 3) {
                    nextGeneration[row] += "X";
                } else {
                    nextGeneration[row] += firstGeneration[row].charAt(i);
                }
            }
        }
        return nextGeneration;
    }

    public static int getNeighbors(String above, String same, String below, String left, String right) {
        int counter = 0;
        if (above != null) {
            for (char x : above.toCharArray()) {
                if (x == 'X') {
                    counter++;
                }
            }
        }
        if (below != null) {
            for (char x : below.toCharArray()) {
                if (x == 'X') {
                    counter++;
                }
            }
        }
        if (left != null) {
            for (char x : left.toCharArray()) {
                if (x == 'X') {
                    counter++;
                }
            }
        }
        if (right != null) {
            for (char x : right.toCharArray()) {
                if (x == 'X') {
                    counter++;
                }
            }
        }
        for (char x : same.toCharArray()) {
            if (x == 'X') {
                counter++;
            }
        }
        return counter;
    }

    public static String getResult() {
        String result = Arrays.deepToString(universe.getCellHabitat());
        result = result
                .replaceAll(", ", "")
                .replaceAll("\\[", "")
                .replaceAll("]", "");
        return result;
    }

    public static void print(String[] dish) {
        FileManager fileManager = new FileManager();
        fileManager.saveResult(getResult());
        for (String s : dish) {
            System.out.println(s);
        }
    }
}
