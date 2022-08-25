/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author ksmnote
 */
public class Ship {

    private int[][] shipCells;

    public Ship(int[][] shipCells) {
        this.shipCells = shipCells;
    }

    public int[][] getShipCells() {
        return shipCells;
    }

    public int getShipSize() {
        return shipCells.length;
    }

    public int[] getShoot(int[] shipCell) {
        for (int i = 0; i < shipCells.length; i++) {
            if (shipCells[i][0] == shipCell[0] && shipCells[i][1] == shipCell[1]) {
                return shipCell;
            }
        }
        return null; //null - промах
    }

    public boolean getCollision(Ship anotherShip) {

        return true;
    }

}
