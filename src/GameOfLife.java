import Core.Grid;

import java.util.Scanner;

public class GameOfLife {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int s = scanner.nextInt();
        int m = scanner.nextInt();
        Grid grid = new Grid(n, s);
        grid.displayGrid();
        for (int i = 0; i < m; i++) {
            grid.updateGeneration();
            grid.displayGrid();
        }
    }
}