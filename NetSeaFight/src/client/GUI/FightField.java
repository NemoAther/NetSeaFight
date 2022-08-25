package client.GUI;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author ksmnote
 */
public class FightField {

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
