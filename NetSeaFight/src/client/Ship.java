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
//класс нужен только для кораблей в процессе расстановки

    private final int[][] shipCells;

    public Ship(int[][] shipCells) {
        this.shipCells = shipCells; //координаты головы и смещения относительно головы в остальных клетках.
        //для "поднятых" кораблей головой будет становиться левая верхняя клетка или же корабль будет просто удаляться в ангар
    }

    public int[][] getShipCells() {
        return shipCells;
    }

    public int getShipSize() {
        return shipCells.length;
    }

    public void getAuraCollision(Ship ship) {
    }

}
