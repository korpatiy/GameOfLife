package life;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {

    JLabel genLabel = new JLabel();
    JLabel aliveLabel = new JLabel();
    JPanel gridPanel = new JPanel();
    Grid grid;

    private void initializeComponents() {
        genLabel.setName("GenerationLabel");
        genLabel.setBounds(5, 5, 100, 10);
        add(genLabel);

        aliveLabel.setName("AliveLabel");
        aliveLabel.setBounds(5, 15, 50, 10);
        add(aliveLabel);

        gridPanel.setBounds(0, 30, 300, 250);
        gridPanel.setLayout(null);
        gridPanel.setBackground(Color.GRAY);
        add(gridPanel);
    }

    public GameOfLife() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);

        grid = new Grid(8);
        initializeComponents();
        setLayout(null);
        setVisible(true);
        generateGame();
    }

    private void generateGame() {
        for (int i = 0; i < 8; i++) {
            genLabel.setText("Generation #" + (i + 1));
            aliveLabel.setText("Alive: " + grid.getAlive());
            paintGrid();
            grid.updateGeneration();
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void paintGrid() {
        Cell[][] field = grid.getGrid();
        gridPanel.removeAll();
        gridPanel.setLayout(new GridLayout(field.length, field.length));
        for (var e1 : field) {
            for (var e2 : e1) {
                JPanel cell = new JPanel();
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                cell.setBackground(e2.getState() ? Color.BLACK : Color.WHITE);
                gridPanel.add(cell);
            }
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    public static void main(String[] args) {
        new GameOfLife();
    }
}

