package life;

import java.util.Random;

public class Grid {

    private Cell[][] grid;
    private Cell[][] grid1;
    private int height;
    private int width;

    public Grid(int size) {
        height = width = size;
        grid = new Cell[height][width];
        Random random = new Random();
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

    public int getAlive() {
        int count = 0;
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++)
                if (grid[h][w].getState())
                    count++;
        return count;
    }

    public void updateGeneration() {
        checkNeighbors();
        updateGrid();
    }

    private boolean isAlive(int h, int w) {
        return grid[h][w].getState();
    }

    private int countNeighbors(int row, int col) {
        int[] dirX = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dirY = new int[]{-1, 0, 1, 1, -1, -1, 0, 1};
        int count = 0;
        for (int i = 0; i < dirY.length; ++i) {
            int newRow = (row + dirY[i] + getSize()) % getSize();
            int newCol = (col + dirX[i] + getSize()) % getSize();

            if (grid[newRow][newCol].getState()) {
                ++count;
            }
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