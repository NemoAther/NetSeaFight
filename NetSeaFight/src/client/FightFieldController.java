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
public class FightFieldController {

    private CellState[][] field = new CellState[10][10];
    private ArrayList<Ship> ships = new ArrayList<>();
    private final int gridSize;

    public FightFieldController(int gridSize) {
        this.gridSize = gridSize;
        field[0][0] = CellState.SHIP;
        field[5][5] = CellState.SHIP;
        field[6][5] = CellState.SHIP;
        field[9][9] = CellState.SHIP;
        field[9][8] = CellState.SHIP;
    }

    public void placeShip(Ship ship) { //превращаем "относительный" корабль в "абсолютный"
        int x = ship.getShipCells()[0][0];
        int y = ship.getShipCells()[0][1];
        field[ship.getShipCells()[0][0]][ship.getShipCells()[0][1]] = CellState.SHIP; //установка головы корабля
        for (int i = 0; i < ship.getShipSize(); i++) { //установка остальных клеток по смещению от головы
            x = x + ship.getShipCells()[i][0];
            y = y + ship.getShipCells()[i][1];

            field[x][y] = CellState.SHIP;
            if (i == 0) {
                setAuraOne(x, y);
            } else {
                setAura(x, y, ship.getShipCells()[i][0], ship.getShipCells()[i][1]);
            }

        }

        //аура рисуется параллельно кораблю по алгоритму:
        /*
        если сдвиг +х, то рисуется три клетки вправо
        если сдвиг -х, то рисуется три клетки влево
        если сдвиг +у, то рисуется три клетки вниз
        если сдвиг -у, то рисуется три клетки вверх
        диагональных сдвигов не бывает.        
         */
    }

    public void placeShipOLD(Ship ship) { //установка корабля после проверки коллизий
        int x = ship.getShipCells()[0][0];
        int y = ship.getShipCells()[0][1];
        field[ship.getShipCells()[0][0]][ship.getShipCells()[0][1]] = CellState.SHIP; //установка головы корабля
        for (int i = 0; i < ship.getShipSize(); i++) { //установка остальных клеток по смещению от головы
            x = x + ship.getShipCells()[i][0];
            y = y + ship.getShipCells()[i][1];

            field[x][y] = CellState.SHIP;
            if (i == 0) {
                setAuraOne(x, y);
            } else {
                setAura(x, y, ship.getShipCells()[i][0], ship.getShipCells()[i][1]);
            }

        }

        //аура рисуется параллельно кораблю по алгоритму:
        /*
        если сдвиг +х, то рисуется три клетки вправо
        если сдвиг -х, то рисуется три клетки влево
        если сдвиг +у, то рисуется три клетки вниз
        если сдвиг -у, то рисуется три клетки вверх
        диагональных сдвигов не бывает.        
         */
    }

    private void setAuraOne(int x, int y) {
        field[x - 1][y - 1] = CellState.SHIPAURA;
        field[x][y - 1] = CellState.SHIPAURA;
        field[x + 1][y - 1] = CellState.SHIPAURA;
        field[x + 1][y] = CellState.SHIPAURA;
        field[x + 1][y + 1] = CellState.SHIPAURA;
        field[x][y + 1] = CellState.SHIPAURA;
        field[x - 1][y + 1] = CellState.SHIPAURA;
        field[x - 1][y] = CellState.SHIPAURA;
    }

    private void setAura(int x, int y, int x1, int y1) {
        switch (x1) {
            case -1:
                setAuraLeft(x, y);
                break;
            case 1:
                setAuraRight(x, y);
                break;
        }
        switch (y1) {
            case -1:
                setAuraTop(x, y);
                break;
            case 1:
                setAuraBottom(x, y);
                break;
        }
    }

    private void setAuraLeft(int x, int y) {
        field[x - 1][y - 1] = CellState.SHIPAURA;
        field[x - 1][y] = CellState.SHIPAURA;
        field[x - 1][y + 1] = CellState.SHIPAURA;
    }

    private void setAuraRight(int x, int y) {
        field[x + 1][y - 1] = CellState.SHIPAURA;
        field[x + 1][y] = CellState.SHIPAURA;
        field[x + 1][y + 1] = CellState.SHIPAURA;
    }

    private void setAuraTop(int x, int y) {
        field[x - 1][y - 1] = CellState.SHIPAURA;
        field[x][y - 1] = CellState.SHIPAURA;
        field[x + 1][y - 1] = CellState.SHIPAURA;
    }

    private void setAuraBottom(int x, int y) {
        field[x - 1][y + 1] = CellState.SHIPAURA;
        field[x][y + 1] = CellState.SHIPAURA;
        field[x + 1][y + 1] = CellState.SHIPAURA;
    }

    public void removeShip(Ship ship) {

    }

    private void refreshAura() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (field[i][j] == CellState.SHIP) {
                    //чтобы рассчитать ауру нужно найти все корабли (записать их в виде "голова + смещения")
                    //и провести по ним auraone+aura
                    //иначе получится сложный рекурсивный алгоритм, который я до старости не напишу
                    //корабли можно искать и по-очереди, главное запоминать все проверенные клетки чтоб повторов не было
                }
            }
        }
    }

    public void getShoot(int[] cellIndex) {
        if (field[cellIndex[0]][cellIndex[1]] == CellState.SHIP) {
            field[cellIndex[0]][cellIndex[1]] = CellState.HIT;
            System.out.println("попал!");
        }
        if (field[cellIndex[0]][cellIndex[1]] == CellState.EMPTY || field[cellIndex[0]][cellIndex[1]] == null) {
            field[cellIndex[0]][cellIndex[1]] = CellState.MISS;
            System.out.println("промазал!");
        }
    }

    public void getCollision(int[][] cellIndex) {

    }

    public CellState[][] getField() {
        return field;
    }

}
