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
        //System.out.println("nextGen");
    }

    public void updateGeneration() {
        checkNeighbors();
        updateGrid();
    }

    private boolean isAlive(int h, int w) {
        return grid[h][w].getState();
    }

    private int countCorners(int row, int col) {
        int count = 0;

        //top left corner
        if (row == 0 && col == 0) {
            //NW
            if (isAlive(height - 1, width - 1))
                count++;
            /*//W
            if (isAlive(row, width - 1))
                count++;*/
        }
        //top right corner
        if (row == 0 && col == width - 1) {
            //NE
            if (isAlive(height - 1, 0))
                count++;
          /*  //E
            if (isAlive(row, 0))
                count++;*/
        }
        //bot left corner
        if (row == height - 1 && col == 0) {
            //SW
            if (isAlive(0, width - 1))
                count++;
            //W
           /* if (isAlive(row, width - 1))
                count++;
            //*/
        }
        //bot right corner
        if (row == height - 1 && col == width - 1) {
            //SE
            if (isAlive(0, 0))
                count++;
            /*//E
            if (isAlive(row, 0))
                count++;*/
        }

        return count;
    }

    private int countNeighbors(int row, int col) {
        int count = 0;

        count += countCorners(row, col);

        //AROUND

        //NW
        if (row == 0 && col != 0) {
            if (isAlive(height - 1, col - 1))
                count++;
        }
        //N
        if (row == 0) {
            if (isAlive(height - 1, col))
                count++;
        }
        //NE
        if (row == 0 && col != width - 1) {
            if (isAlive(height - 1, col + 1))
                count++;
        }

        //SW
        if (row == height - 1 && col != 0) {
            if (isAlive(0, col - 1))
                count++;
        }
        //s
        if (row == height - 1) {
            if (isAlive(0, col))
                count++;
        }
        //SE
        if (row == height - 1 && col != width - 1) {
            if (isAlive(0, col + 1))
                count++;
        }

        //NW
        if (col == 0 && row != 0) {
            if (isAlive(row - 1, width - 1))
                count++;
        }
        //W
        if (col == 0) {
            if (isAlive(row, width - 1))
                count++;
        }
        //SW
        if (col == 0 && row != height - 1) {
            if (isAlive(row + 1, width - 1))
                count++;
        }

        //NE
        if (col == width - 1 && row != 0) {
            if (isAlive(row - 1, 0))
                count++;
        }
        //E
        if (col == width - 1) {
            if (isAlive(row, 0))
                count++;
        }
        //SE
        if (col == width - 1 && row != height - 1) {
            if (isAlive(row + 1, 0))
                count++;
        }

        //INSIDE

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

        //W
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
                if (count < 2 || count > 3)
                    grid[h][w].setNewState(false);
                else if (count == 3)
                    grid[h][w].setNewState(true);
            }
        }
    }

    private void updateGrid() {
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++)
                grid[h][w].updateState();
        }
    }
}