package display;

import life.Cell;

import java.io.IOException;


public class Display {
    private Cell[][] grid;
    private static int gen = 0;
    private int alive;

    public Display(Cell[][] grid, int alive) {
        this.grid = grid;
        gen++;
        this.alive = alive;
    }

    private void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException e) {
        }
    }

    public void display() {
        clearScreen();
        System.out.println("Generation: #" + gen);
        System.out.println();
        System.out.println("Alive: " + alive);
        System.out.println();
        for (int h = 0; h < grid.length; h++) {
            for (int w = 0; w < grid.length; w++) {
                if (grid[h][w].getState())
                    System.out.print('O');
                else
                    System.out.print(' ');
            }
            System.out.println();
        }
    }

}

