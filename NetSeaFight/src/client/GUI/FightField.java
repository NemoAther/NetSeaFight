package client.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

/**
 *
 * @author ksmnote
 */
public class FightField extends JPanel implements Runnable {

    int gridSize = 10;
    int cellSize = 30;
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;
    Point cursor = new Point(0, 0);
    volatile int x1 = 0;
    volatile int y1 = 0;
    volatile int x2 = 5;
    volatile int y2 = 5;

    int xSelect = 0;
    int ySelect = 0;

    public FightField() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        Thread redraw = new Thread(this);
        redraw.start();
    }

    @Override
    public void run() {
        while (true) {
            repaint();

        }
    }
//почитать про буфферед имейдж

    void draw(Graphics g) {
        //g.setColor(Color.red);
        //g.drawLine(x1, y1, x2, y2);
        //g.drawRect(x1, y1, x2, y2);
        drawGrid(g);
        g.setColor(Color.red);
        if (x1 > cellSize && x1 < cellSize * (gridSize + 1)) {
            if (y1 > cellSize && y1 < cellSize * (gridSize + 1)) {
                xSelect = ((int) x1 / cellSize) * cellSize;
                ySelect = ((int) y1 / cellSize) * cellSize;
            }
        }
        g.drawRect(xSelect, ySelect, cellSize, cellSize);
        drawShipHangar(g);

    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.yellow);
        //g.drawLine(10, 10, 310, 10);
        for (int i = 1; i < gridSize + 2; i++) {
            g.drawLine(cellSize, i * cellSize, cellSize * (gridSize + 1), i * cellSize);
            g.drawLine(i * cellSize, cellSize, i * cellSize, cellSize * (gridSize + 1));
        }
    }

    private void drawShipHangar(Graphics g) {
        //4-хклеточный
        g.setColor(Color.white);
        g.fillRect(500, cellSize, cellSize * 4, cellSize);
        g.setColor(Color.black);
        g.drawLine(500 + cellSize, cellSize, 500 + cellSize, cellSize * 2);
        g.drawLine(500 + cellSize * 2, cellSize, 500 + cellSize * 2, cellSize * 2);
        g.drawLine(500 + cellSize * 3, cellSize, 500 + cellSize * 3, cellSize * 2);
        //3-хклеточный
        g.setColor(Color.white);
        g.fillRect(500, cellSize * 3, cellSize * 3, cellSize);
        g.setColor(Color.black);
        g.drawLine(500 + cellSize, cellSize * 3, 500 + cellSize, cellSize * 4);
        g.drawLine(500 + cellSize * 2, cellSize * 3, 500 + cellSize * 2, cellSize * 4);
        //2-хклеточный
        g.setColor(Color.white);
        g.fillRect(500, cellSize * 5, cellSize * 2, cellSize);
        g.setColor(Color.black);
        g.drawLine(500 + cellSize, cellSize * 5, 500 + cellSize, cellSize * 6);
        //1-клеточный
        g.setColor(Color.white);
        g.fillRect(500, cellSize * 7, cellSize, cellSize);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // paint background
        setBackground(Color.BLACK);
        draw(g);
    }
}
