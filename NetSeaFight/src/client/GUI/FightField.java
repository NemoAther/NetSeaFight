package client.GUI;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author ksmnote
 */
public class FightField extends JPanel {

    private final GameScreen gameScreen;

    private final int gridSize;
    private final int cellSize;
    private final int fieldSize;

    int xSelect = 0;
    int ySelect = 0;

    public FightField(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        cellSize = gameScreen.getCellSize();
        fieldSize = cellSize * 12;
        gridSize = gameScreen.getGridSize();
    }

//почитать про буфферед имейдж
    void draw(Graphics g) {
        drawGrid(g);
        drawSelected(g);
    }

    void drawSelected(Graphics g) {
        g.setColor(Color.red);
        if (gameScreen.getCursorX() > cellSize && gameScreen.getCursorX() < cellSize * (gridSize + 1)) {
            if (gameScreen.getCursorY() > cellSize && gameScreen.getCursorY() < cellSize * (gridSize + 1)) {
                xSelect = ((int)gameScreen.getCursorX() / cellSize) * cellSize;
                ySelect = ((int)gameScreen.getCursorY() / cellSize) * cellSize;
            }
        }
        g.drawRect(xSelect, ySelect, cellSize, cellSize);
    }

    void drawGrid(Graphics g) {
        g.setColor(Color.yellow);
        for (int i = 1; i < gridSize + 2; i++) {
            g.drawLine(cellSize, i * cellSize, cellSize * (gridSize + 1), i * cellSize);
            g.drawLine(i * cellSize, cellSize, i * cellSize, cellSize * (gridSize + 1));
        }
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
