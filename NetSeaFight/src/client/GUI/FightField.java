package client.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 *
 * @author ksmnote
 */
public class FightField extends JPanel implements Runnable {

    int gridSize = 10;
    int cellSize = 30;
    final int fieldSize = cellSize * 12;
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;
    Point cursor = new Point(0, 0);
    volatile int x1 = 0;
    volatile int y1 = 0;
    volatile int x2 = 5;
    volatile int y2 = 5;

    int whatDragged = 0;

    int xSelect = 0;
    int ySelect = 0;

    public FightField() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (x1 < fieldSize) {
                    //работаем с полем 
                }
                if (x1 >= fieldSize) {
                    hangarOnClick();//работаем с ангаром
                }
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                cursor = e.getLocationOnScreen();
                x1 = e.getX();
                y1 = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }
        });
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

        if (whatDragged > 0) {
            drawShipOnCursor(g);
        }

    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.yellow);
        //g.drawLine(10, 10, 310, 10);
        for (int i = 1; i < gridSize + 2; i++) {
            g.drawLine(cellSize, i * cellSize, cellSize * (gridSize + 1), i * cellSize);
            g.drawLine(i * cellSize, cellSize, i * cellSize, cellSize * (gridSize + 1));
        }
    }

    private void drawShipOnCursor(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x1, y1, cellSize * 4, cellSize);
        g.setColor(Color.black);
        g.drawLine(x1 + cellSize * 1, y1, x1 + cellSize * 1, y1 + cellSize);
        g.drawLine(x1 + cellSize * 2, y1, x1 + cellSize * 2, y1 + cellSize);
        g.drawLine(x1 + cellSize * 3, y1, x1 + cellSize * 3, y1 + cellSize);

        //g.fillRect(x1, y1, 10, 10);
    }

    private void drawShipHangar(Graphics g) {

        //4-хклеточный
        g.setColor(Color.white);
        g.fillRect(fieldSize, cellSize, cellSize * 4, cellSize);
        g.setColor(Color.black);
        g.drawLine(fieldSize + cellSize, cellSize, fieldSize + cellSize, cellSize * 2);
        g.drawLine(fieldSize + cellSize * 2, cellSize, fieldSize + cellSize * 2, cellSize * 2);
        g.drawLine(fieldSize + cellSize * 3, cellSize, fieldSize + cellSize * 3, cellSize * 2);
        //3-хклеточный
        g.setColor(Color.white);
        g.fillRect(fieldSize, cellSize * 3, cellSize * 3, cellSize);
        g.setColor(Color.black);
        g.drawLine(fieldSize + cellSize, cellSize * 3, fieldSize + cellSize, cellSize * 4);
        g.drawLine(fieldSize + cellSize * 2, cellSize * 3, fieldSize + cellSize * 2, cellSize * 4);
        //2-хклеточный
        g.setColor(Color.white);
        g.fillRect(fieldSize, cellSize * 5, cellSize * 2, cellSize);
        g.setColor(Color.black);
        g.drawLine(fieldSize + cellSize, cellSize * 5, fieldSize + cellSize, cellSize * 6);
        //1-клеточный
        g.setColor(Color.white);
        g.fillRect(fieldSize, cellSize * 7, cellSize, cellSize);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // paint background
        setBackground(Color.BLACK);
        draw(g);
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getCellSize() {
        return cellSize;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    private void hangarOnClick() {
        if (x1 >= fieldSize && x1 <= fieldSize + cellSize * 4 && y1 >= cellSize && y1 <= cellSize * 2) {
            System.out.println("клик в ангаре");
            if (whatDragged == 0) {
                whatDragged = 4;

            }
        }
    }
}
