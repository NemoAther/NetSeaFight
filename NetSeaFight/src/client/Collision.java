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

    int cellSize;
    int fieldSize;
    ArrayList<Ship> ships = new ArrayList<>(10);

    public Collision(int cellSize, int fieldSize) {
        this.cellSize = cellSize;
        this.fieldSize = fieldSize;
        int[][] testCells = {{1, 1}, {1, 2}};
        //Ship testShip = new Ship();
        ships.add(new Ship(testCells));
}

    public void getCollision(double cursorX, double cursorY) {
        
        if (cursorX <= fieldSize) {//работаем с полем 
            int[] cellIndex = getCellIndex(cursorX, cursorY); //получаем координаты не в пикселях, а в ячейках
            //System.out.println("mouse sellIndex=" + cellIndex[0] + " " + cellIndex[1]);
            for (int i = 0; i < ships.size(); i++) {
                //System.out.println("mouse ship №" + i);
                if (ships.get(i) != null) {
                    /*for (int j = 0; j < ships.get(i).getShipSize(); j++) {
                        //System.out.println("mouse cell №" + j);
                        System.out.println("shipCellIndex=" + ships.get(i).getShipCells()[j][0] + " " + ships.get(i).getShipCells()[j][1]);
                        if (cellIndex[0] == ships.get(i).getShipCells()[j][0] && cellIndex[1] == ships.get(i).getShipCells()[j][1]) {
                            System.out.println("Занято");
                        }
                    }*/
                    ships.get(i).getShoot(cellIndex);
                }
            }
        }
        if (cursorX >= fieldSize) { //работаем с ангаром

        }
    }

    int[] getCellIndex(double cursorX, double cursorY) {
        int[] cellIndex = new int[2];

        cellIndex[0] = (int) (cursorX / cellSize);
        cellIndex[1] = (int) (cursorY / cellSize);
        return cellIndex;
    }
}
