package life;

import display.Display;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Grid grid = new Grid(n);
        Display d;
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            grid.updateGeneration();
            d = new Display(grid.getGrid(), grid.getAlive());
            d.display();
        }
    }
}