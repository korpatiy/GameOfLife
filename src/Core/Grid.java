package Core;

import java.util.Random;

public class Grid {

    private Cell[][] grid;
    private Cell[][] grid1;
    private int height;
    private int width;

    public Grid(int size, int s) {
        height = width = size;
        grid = new Cell[height][width];
        Random random = new Random(s);
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++) {
                grid[h][w] = new Cell();
                if (random.nextBoolean()) {
                    grid[h][w].setNewState(true);
                    grid[h][w].updateState();
                }
            }
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public int getSize() {
        return height;
    }

    public void displayGrid() {
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                if (grid[h][w].getState())
                    System.out.print('O');
                else
                    System.out.print(' ');
            }
            System.out.println();
        }
    }


    public void updateGeneration() {
        checkNeighbors();
    }

    private boolean isAlive(int h, int w) {
        return grid[h][w].getState();
    }

    private int countNeighbors(int row, int col) {
        int count = 0;

        //NW and not corner(left top)
        if (row != 0 && col != 0) {
            if (isAlive(row - 1, col - 1))
                count++;
        }

        //N and not corner
        if (row != 0) {
            if (isAlive(row - 1, col))
                count++;
        }

        //NE and not corner(right top)
        if (row != 0 && col != width - 1) {
            if (isAlive(row - 1, col + 1))
                count++;
        }

        //E
        if (col != width - 1) {
            if (isAlive(row, col + 1))
                count++;
        }

        //SE and not corner
        if (row != height - 1 && col != width - 1) {
            if (isAlive(row + 1, col + 1))
                count++;
        }

        //S
        if (row != height - 1) {
            if (isAlive(row + 1, col))
                count++;
        }

        //SW and not corner
        if (row != height - 1 && col != 0) {
            if (isAlive(row + 1, col - 1))
                count++;
        }

        //S
        if (col != 0) {
            if (isAlive(row, col - 1))
                count++;
        }

        return count;
    }

    private void checkNeighbors() {
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                int count = countNeighbors(h, w);
                
            }
        }
    }
}