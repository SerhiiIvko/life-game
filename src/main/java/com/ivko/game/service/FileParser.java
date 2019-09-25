package com.ivko.game.service;

import com.ivko.game.model.Universe;

public class FileParser {

    public Universe getUniverseFromFile() {
        String[] data = splitFile();
        int width = parseWidth(data);
        int height = parseHeight(data);
        int iterations = parseIterations(data);
        char[][] cells = parseCellMatrix(data);
        return new Universe(cells, height, width, iterations);
    }

    private String[] splitFile() {
        FileManager fileManager = new FileManager();
        String data = fileManager.loadData();
        return data.split(";");
    }

    private int parseWidth(String[] data) {
        String width = data[0].replaceAll("\\D+", "");
        return Integer.parseInt(width);
    }

    private int parseHeight(String[] data) {
        String height = data[1].replaceAll("\\D+", "");
        return Integer.parseInt(height);
    }

    private int parseIterations(String[] data) {
        String iterations = data[2].replaceAll("\\D+", "");
        return Integer.parseInt(iterations);
    }

    private char[][] parseCellMatrix(String[] data) {
        String[] components = data[3].trim().split(", ");
        char[][] outCharArray = new char[components.length][];
        for (int i = 0; i < components.length; i++) {
            String component = components[i];
            outCharArray[i] = component.toCharArray();
        }
        return outCharArray;
    }

//    public static void main(String[] args) {
//        FileManagerImpl fileManager = new FileManagerImpl();
//        String data = fileManager.loadData();
//        String[] splitedFile = data.split(";");
//        System.out.println(Arrays.toString(splitedFile));
//        String width = splitedFile[0].replaceAll("\\D+", "");
//        String height = splitedFile[1].replaceAll("\\D+", "");
//        String iterations = splitedFile[2].replaceAll("\\D+", "");
//        System.out.println(width);
//        System.out.println(height);
//        System.out.println(iterations);
//        int w = Integer.parseInt(width);
//        int h = Integer.parseInt(height);
//        int iter = Integer.parseInt(iterations);

//        String[] components = splitedFile[3].trim().split(", ");
//        char [][] outCharArray = new char[components.length][];
//        for (int i = 0; i < components.length; i++) {
//            String component = components[i];
//            outCharArray[i] = component.toCharArray();
//        }
//
//        String result = Arrays.deepToString(outCharArray);
//        result = result
//                .replaceAll(", ", "")
//                .replaceAll("\\[", "")
//                .replaceAll("]", "");
//
//        System.out.println(result);
//    }
}
