/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.ArrayList;

/**
 *
 * @author ksmnote
 */
public class Collision {

    private final int cellSize;
    private final int fieldSize;
    private final FightFieldController fightFieldController;

    public Collision(int cellSize, int fieldSize, FightFieldController fightFieldController) {
        this.cellSize = cellSize;
        this.fieldSize = fieldSize;
        this.fightFieldController = fightFieldController;
    }

    public void getCollision(double cursorX, double cursorY, int sizeDragged, int formDragged) {
        if (cursorX <= fieldSize) {//работаем с полем 
            int[] cellIndex = getCellIndex(cursorX, cursorY); //получаем координаты не в пикселях, а в ячейках
            fightFieldController.getShoot(cellIndex);
        } else {
            //работаем с ангаром или вражеским полем
        }
    }

    int[] getCellIndex(double cursorX, double cursorY) {
        int[] cellIndex = new int[2];

        cellIndex[0] = (int) (cursorX / cellSize);
        cellIndex[1] = (int) (cursorY / cellSize);
        return cellIndex;
    }
}
