package client.GUI;

import client.ShipHangarController;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author GAV
 */
public class ShipHangar {

    private final GameScreen gameScreen;
    private final int hangarPosition;
    private final int cellSize;
    private final ShipHangarController controller;

    ShipHangar(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        cellSize = gameScreen.getCellSize();
        hangarPosition = gameScreen.fightFieldGUI.getFieldSize() + cellSize;
        controller = new ShipHangarController();
    }

    public void draw(Graphics g) {
        drawShipHangar(g);
    }

    public void drawShipOnCursor(Graphics g) {
        drawShip(g, gameScreen.getCursorX(), gameScreen.getCursorY(), gameScreen.getDraggedSize(), gameScreen.getDraggedForm());
    }

    public void drawShip(Graphics g, int positionX, int positionY, int shipSize, int shipForm) {
        if (controller.getShipQuantity(shipSize)) {
            g.setColor(Color.white);
        } else {
            g.setColor(Color.gray);
        }
        switch (shipForm) {
            case 0:
                drawHorizontal(g, positionX, positionY, shipSize);
                break;
            case 1:
                drawVertical(g, positionX, positionY, shipSize);
                break;
        }
    }

    void drawHorizontal(Graphics g, int positionX, int positionY, int shipSize) {
        g.fillRect(positionX, positionY, cellSize * shipSize, cellSize);
        g.setColor(Color.black);
        for (int i = 1; i < shipSize; i++) {
            g.drawLine(positionX + cellSize * i, positionY, positionX + cellSize * i, positionY + cellSize);
        }
    }

    void drawVertical(Graphics g, int positionX, int positionY, int shipSize) {
        g.fillRect(positionX, positionY, cellSize, cellSize * shipSize);
        g.setColor(Color.black);
        for (int i = 1; i < shipSize; i++) {
            g.drawLine(positionX, positionY + cellSize * i, positionX + cellSize, positionY + cellSize * i);
        }
    }

    private void drawShipHangar(Graphics g) {
        drawShip(g, hangarPosition, cellSize, 4, 0);
        drawShip(g, hangarPosition, cellSize * 3, 3, 0);
        drawShip(g, hangarPosition, cellSize * 5, 2, 0);
        drawShip(g, hangarPosition, cellSize * 7, 1, 0);
    }

    public void hangarOnClick(double cursorX, double cursorY, int sizeDragged, int formDragged) {
        if (sizeDragged == 0) {
            if (cursorX > hangarPosition && cursorX <= hangarPosition + cellSize * 4) {
                if (cursorY >= cellSize && cursorY <= cellSize * 2) {
                    if (controller.getShip(4)) {
                        gameScreen.setDraggedSize(4);
                    }
                }
                if (cursorY >= cellSize * 3 && cursorY <= cellSize * 4) {
                    if (controller.getShip(3)) {
                        gameScreen.setDraggedSize(3);
                    }
                }
                if (cursorY >= cellSize * 5 && cursorY <= cellSize * 6) {
                    if (controller.getShip(2)) {
                        gameScreen.setDraggedSize(2);
                    }
                }
                if (cursorY >= cellSize * 7 && cursorY <= cellSize * 8) {
                    if (controller.getShip(1)) {
                        gameScreen.setDraggedSize(1);
                    }
                }
            }
        } else {
            controller.returnShip(sizeDragged);
            gameScreen.setDraggedSize(0);
        }
        gameScreen.setDraggedForm(0);
    }
}
