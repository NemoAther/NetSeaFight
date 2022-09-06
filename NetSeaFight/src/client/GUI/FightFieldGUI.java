package client.GUI;

import client.CellState;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author ksmnote
 */
public class FightFieldGUI {

    private final GameScreen gameScreen;

    private final int gridSize;
    private final int cellSize;
    private final int fieldSize;

    int xSelect = 0;
    int ySelect = 0;

    public FightFieldGUI(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        cellSize = gameScreen.getCellSize();
        fieldSize = cellSize * 10;
        gridSize = gameScreen.getGridSize();
    }

//почитать про буфферед имейдж
    void draw(Graphics g, CellState[][] field) {
        drawField(g, field);
        drawGrid(g);
        drawSelected(g);
    }

    void drawSelected(Graphics g) {
        g.setColor(Color.red);
        if (gameScreen.getCursorX() > 0 && gameScreen.getCursorX() < cellSize * gridSize) {
            if (gameScreen.getCursorY() > 0 && gameScreen.getCursorY() < cellSize * gridSize) {
                xSelect = ((int) gameScreen.getCursorX() / cellSize) * cellSize;
                ySelect = ((int) gameScreen.getCursorY() / cellSize) * cellSize;
            }
        }
        g.drawRect(xSelect, ySelect, cellSize, cellSize);
    }

    void drawGrid(Graphics g) {
        g.setColor(Color.yellow);
        for (int i = 0; i < gridSize + 1; i++) {
            g.drawLine(0, i * cellSize, cellSize * gridSize, i * cellSize);
            g.drawLine(i * cellSize, 0, i * cellSize, cellSize * gridSize);
        }
    }

    void drawField(Graphics g, CellState[][] field) {
        g.setColor(Color.DARK_GRAY);
        for (int column = 0; column < field.length; column++) {
            for (int row = 0; row < field[0].length; row++) {
                    drawFilledCell(g, column, row, field[column][row]);
            }
        }
    }

    void drawFilledCell(Graphics g, int columnNumber, int rowNumber, CellState cellState) {
        if (cellState == null) {
            cellState = CellState.EMPTY;
        }
        switch (cellState) {
            case SHIP:
                g.setColor(Color.WHITE); break;
            case SHIPAURA:
                g.setColor(Color.GRAY); break;
            case EMPTY:
                g.setColor(Color.BLUE); break;
            case MISS:
                g.setColor(Color.RED); break;
            case HIT:
                g.setColor(Color.GREEN); break;
            case MISSAURA:
                g.setColor(Color.CYAN); break;
        }
        g.fillRect(columnNumber * cellSize, rowNumber * cellSize, cellSize, cellSize);
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
