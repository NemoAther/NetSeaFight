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
        hangarPosition = gameScreen.fightField.getFieldSize() + cellSize;
    }

    public void draw(Graphics g) {
        drawShipHangar(g);
    }

    public void drawShipOnCursor(Graphics g) {
        drawShip(g, gameScreen.getCursorX(), gameScreen.getCursorY(), gameScreen.getWhatDragged());
    }

    private void drawShip(Graphics g, int positionX, int positionY, int shipSize) {
        g.setColor(Color.white);
        g.fillRect(positionX, positionY, cellSize * shipSize, cellSize);
        g.setColor(Color.black);
        for (int i = 1; i < shipSize; i++) {
            g.drawLine(positionX + cellSize * i, positionY, positionX + cellSize * i, positionY + cellSize);
        }
    }

    private void drawShipHangar(Graphics g) {

        drawShip(g, hangarPosition, cellSize, 4);
        drawShip(g, hangarPosition, cellSize * 3, 3);
        drawShip(g, hangarPosition, cellSize * 5, 2);
        drawShip(g, hangarPosition, cellSize * 7, 1);

    }

    public void hangarOnClick() {

    }
}
