/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.GUI;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author ksmnote
 */
public class ShipHangar {

    private GameScreen gameScreen;
    private int hangarPosition;
    private int cellSize;

    ShipHangar(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        cellSize = gameScreen.getCellSize();
        hangarPosition = gameScreen.fightFieldGUI.getFieldSize() + cellSize;
    }

    public void draw(Graphics g) {
        drawShipHangar(g);
    }

    public void drawShipOnCursor(Graphics g) {
        drawShip(g, gameScreen.getCursorX(), gameScreen.getCursorY(), gameScreen.getDraggedSize(), gameScreen.getDraggedForm());
    }

    public void drawShip(Graphics g, int positionX, int positionY, int shipSize, int shipForm) {
        g.setColor(Color.white);
        switch (shipForm) {
            case 0:
                drawHorizontal(g, positionX, positionY, shipSize);
                break;
            case 1:
                drawVertical(g, positionX, positionY, shipSize);
                break;
        }
        /*for (int i = 1; i < shipSize; i++) {
            g.drawLine(positionX + cellSize * i, positionY, positionX + cellSize * i, positionY + cellSize);
        }*/
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
        System.out.println("есть клик в ангаре");
        if (cursorX > hangarPosition && cursorX <= hangarPosition + cellSize * 4) {
            if (cursorY >= cellSize && cursorY <= cellSize * 2) {
                gameScreen.setDraggedSize(4);
                gameScreen.setDraggedForm(0);
            }
            if (cursorY >= cellSize * 3 && cursorY <= cellSize * 4) {
                gameScreen.setDraggedSize(3);
                gameScreen.setDraggedForm(0);
            }
            if (cursorY >= cellSize * 5 && cursorY <= cellSize * 6) {
                gameScreen.setDraggedSize(2);
                gameScreen.setDraggedForm(0);
            }
            if (cursorY >= cellSize * 7 && cursorY <= cellSize * 8) {
                gameScreen.setDraggedSize(1);
                gameScreen.setDraggedForm(0);
            }
        }
    }
}
