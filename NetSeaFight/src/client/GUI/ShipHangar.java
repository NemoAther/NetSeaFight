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

    ShipHangar(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public void drawShipOnCursor(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(gameScreen.getCursorX(), gameScreen.getCursorY(), gameScreen.getCellSize() * 4, gameScreen.getCellSize());
        g.setColor(Color.black);
        g.drawLine(gameScreen.getCursorX() + gameScreen.getCellSize() * 1, gameScreen.getCursorY(), gameScreen.getCursorX() + gameScreen.getCellSize() * 1, gameScreen.getCursorY() + gameScreen.getCellSize());
        g.drawLine(gameScreen.getCursorX() + gameScreen.getCellSize() * 2, gameScreen.getCursorY(), gameScreen.getCursorX() + gameScreen.getCellSize() * 2, gameScreen.getCursorY() + gameScreen.getCellSize());
        g.drawLine(gameScreen.getCursorX() + gameScreen.getCellSize() * 3, gameScreen.getCursorY(), gameScreen.getCursorX() + gameScreen.getCellSize() * 3, gameScreen.getCursorY() + gameScreen.getCellSize());

        //g.fillRect(x1, y1, 10, 10);
    }

    private void drawShipHangar(Graphics g) {
/*
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
        g.fillRect(fieldSize, cellSize * 7, cellSize, cellSize);*/
    }

    public void hangarOnClick() {

    }
}
