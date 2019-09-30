package com.ivko.game.service;


import org.junit.Assert;
import org.junit.Test;

public class FileManagerTest {
    private InitialData initialData;

    @Test
    public void loadDataWithCorrectParamsTest() {
        int height = 10;
        int width = 10;
        int iterations = 10;
        boolean[][] result = new boolean[width][height];
        initialData = new InitialData(result, iterations);
        Assert.assertNotNull(initialData);
        Assert.assertEquals(initialData.getIterations(), iterations);
    }

    @Test(expected = NegativeArraySizeException.class)
    public void loadDataWithNotCorrectParamsTest() {
        int height = -1;
        int width = -3;
        int iterations = -4;
        boolean[][] result = new boolean[width][height];
        initialData = new InitialData(result, iterations);
        Assert.assertNotNull(initialData);
    }

    @Test
    public void loadDataIfReturnNullTest() {
        initialData = null;
        Assert.assertNull(initialData);
    }
}