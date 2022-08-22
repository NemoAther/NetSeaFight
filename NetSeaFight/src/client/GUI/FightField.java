package client.GUI;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author ksmnote
 */
public class FightField extends JPanel {

    private GameScreen gameScreen;

    private final int gridSize;
    private final int cellSize;
    private final int fieldSize;
    //public static final int CANVAS_WIDTH = 800;
    //public static final int CANVAS_HEIGHT = 600;
    //Point cursor = new Point(0, 0);
    //volatile int x1 = 0;
    //volatile int y1 = 0;
    //volatile int x2 = 5;
    //volatile int y2 = 5;

    //int whatDragged = 0;
    int xSelect = 0;
    int ySelect = 0;
    int xSelect2 = 0;
    int ySelect2 = 0;

    //ShipHangar hangar;
    public FightField(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        cellSize = gameScreen.getCellSize();
        fieldSize = cellSize * 12;
        gridSize = gameScreen.getGridSize();
        //Thread redraw = new Thread(this);
        //redraw.start();
    }

    /*@Override
    public void run() {
        while (true) {
            repaint();

        }
    }*/
//почитать про буфферед имейдж
    void draw(Graphics g) {

        drawGrid(g);
        g.setColor(Color.red);
        if (gameScreen.getCursorX() > cellSize && gameScreen.getCursorX() < cellSize * (gridSize + 1)) {
            if (gameScreen.getCursorY() > cellSize && gameScreen.getCursorY() < cellSize * (gridSize + 1)) {
                xSelect = ((int) gameScreen.getCursorX() / cellSize) * cellSize;
                ySelect = ((int) gameScreen.getCursorX() / cellSize) * cellSize;
            }
        }
        g.drawRect(xSelect, ySelect, cellSize, cellSize);
        drawShipHangar(g);

    }

    void drawSelected(Graphics g) {
        System.out.println(gameScreen.getCursorX() + " " + gameScreen.getCursorY() + " " + gameScreen.getPoint().getX() + " " + gameScreen.getLocation().getY());
        g.setColor(Color.red);
        if (gameScreen.getCursorX() > cellSize && gameScreen.getCursorX() < cellSize * (gridSize + 1)) {
            if (gameScreen.getCursorY() > cellSize && gameScreen.getCursorY() < cellSize * (gridSize + 1)) {
                xSelect = ((int)gameScreen.getCursorX() / cellSize) * cellSize;
                ySelect = ((int)gameScreen.getCursorY() / cellSize) * cellSize;
                xSelect2 = gameScreen.getCursorX();
                ySelect2 = gameScreen.getCursorY();
            }
        }
        g.drawRect(xSelect, ySelect, cellSize, cellSize);
        g.setColor(Color.green);
        g.drawRect(xSelect2, ySelect2, cellSize, cellSize);
        
    }

    void drawGrid(Graphics g) {
        g.setColor(Color.yellow);
        //g.drawLine(10, 10, 310, 10);
        for (int i = 1; i < gridSize + 2; i++) {
            g.drawLine(cellSize, i * cellSize, cellSize * (gridSize + 1), i * cellSize);
            g.drawLine(i * cellSize, cellSize, i * cellSize, cellSize * (gridSize + 1));
        }
    }

    private void drawShipOnCursor(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(gameScreen.getCursorX(), gameScreen.getCursorY(), cellSize * 4, cellSize);
        g.setColor(Color.black);
        g.drawLine(gameScreen.getCursorX() + cellSize * 1, gameScreen.getCursorY(), gameScreen.getCursorX() + cellSize * 1, gameScreen.getCursorY() + cellSize);
        g.drawLine(gameScreen.getCursorX() + cellSize * 2, gameScreen.getCursorY(), gameScreen.getCursorX() + cellSize * 2, gameScreen.getCursorY() + cellSize);
        g.drawLine(gameScreen.getCursorX() + cellSize * 3, gameScreen.getCursorY(), gameScreen.getCursorX() + cellSize * 3, gameScreen.getCursorY() + cellSize);

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
        //super.paintComponent(g);  // paint background
        //setBackground(Color.BLACK);
        //draw(g);
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

    /*private void hangarOnClick() {
        if (gameScreen.getCursorX() >= fieldSize && gameScreen.getCursorX() <= fieldSize + cellSize * 4 && y1 >= cellSize && y1 <= cellSize * 2) {
            System.out.println("клик в ангаре");
            if (whatDragged == 0) {
                whatDragged = 4;

            }
        }
    }*/
}
