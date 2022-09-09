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

    private final CellState[][] field = new CellState[10][10];
    private final ArrayList<Ship> ships = new ArrayList<>();
    private final int gridSize;

    public FightFieldController(int gridSize) {
        for (int columns = 0; columns < gridSize; columns++) {
            for (int rows = 0; rows < gridSize; rows++) {
                field[columns][rows] = CellState.EMPTY;
            }
        }
        this.gridSize = gridSize;
    }

    public void addShip(int[] cellIndex, int shipSize, int shipForm) {
        checkFreeSpace(cellIndex, shipSize, shipForm);

    }

    public CellState[][] getField() {
        return field;
    }

    public void removeShip(int[] cellIndex) {
        if (field[cellIndex[0]][cellIndex[1]] == CellState.SHIP) {
            searchFullShip(cellIndex[0], cellIndex[1]);
        }
    }

    private int[][] searchFullShip(int x, int y) {
        int[][] shipCells = {{0},{x, y}};
        try {
            if (field[x - 1][y - 1] == CellState.SHIP) {
                System.out.println("test левоверх");
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("выход за пределы массива, но все ок");
        }
        try {
            if (field[x][y - 1] != CellState.EMPTY) {
                System.out.println("test верхцентр");
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("выход за пределы массива, но все ок");
        }
        try {
            if (field[x + 1][y - 1] != CellState.EMPTY) {
                System.out.println("test верхправо");
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("выход за пределы массива, но все ок");
        }
        try {
            if (field[x + 1][y] != CellState.EMPTY) {
                System.out.println("test правоцентр");
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("выход за пределы массива, но все ок");
        }
        try {
            if (field[x + 1][y + 1] != CellState.EMPTY) {
                System.out.println("test правониз");
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("выход за пределы массива, но все ок");
        }
        try {
            if (field[x][y + 1] != CellState.EMPTY) {
                System.out.println("test низцентр");
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("выход за пределы массива, но все ок");
        }
        try {
            if (field[x - 1][y + 1] != CellState.EMPTY) {
                System.out.println("test низлево");
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("выход за пределы массива, но все ок");
        }
        try {
            if (field[x - 1][y] != CellState.EMPTY) {
                System.out.println("test левоцентр");
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("выход за пределы массива, но все ок");
        }

        return null;
    }

    private void checkFreeSpace(int[] cellIndex, int shipSize, int shipForm) {
        switch (shipForm) {
            case 0:
                if (gridSize - cellIndex[0] - shipSize >= 0) {
                    checkShipCollision(cellIndex, shipSize, shipForm);
                }
                break;
            case 1:
                if (gridSize - cellIndex[1] - shipSize >= 0) {
                    checkShipCollision(cellIndex, shipSize, shipForm);
                }
                break;
        }
    }

    private void checkShipCollision(int[] cellIndex, int shipSize, int shipForm) {
        int check = 0;
        for (int i = 0; i < shipSize; i++) {
            switch (shipForm) {
                case 0:
                    if (checkNeighbours(cellIndex[0] + i, cellIndex[1])) {
                        check++;
                    }
                    break;
                case 1:
                    if (checkNeighbours(cellIndex[0], cellIndex[1] + i)) {
                        check++;
                    }
                    break;
            }
        }
        if (check == shipSize) {
            placeShip(cellIndex, shipSize, shipForm);
        }
    }

    private boolean checkNeighbours(int x, int y) {
        if (field[x][y] != CellState.EMPTY) {
            System.out.println("field[x][y]" + field[x][y]);
            System.out.println("test центр");
            return false;
        }
        try {
            if (field[x - 1][y - 1] != CellState.EMPTY) {
                System.out.println("test левоверх");
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("выход за пределы массива, но все ок");
        }
        try {
            if (field[x][y - 1] != CellState.EMPTY) {
                System.out.println("test верхцентр");
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("выход за пределы массива, но все ок");
        }
        try {
            if (field[x + 1][y - 1] != CellState.EMPTY) {
                System.out.println("test верхправо");
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("выход за пределы массива, но все ок");
        }
        try {
            if (field[x + 1][y] != CellState.EMPTY) {
                System.out.println("test правоцентр");
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("выход за пределы массива, но все ок");
        }
        try {
            if (field[x + 1][y + 1] != CellState.EMPTY) {
                System.out.println("test правониз");
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("выход за пределы массива, но все ок");
        }
        try {
            if (field[x][y + 1] != CellState.EMPTY) {
                System.out.println("test низцентр");
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("выход за пределы массива, но все ок");
        }
        try {
            if (field[x - 1][y + 1] != CellState.EMPTY) {
                System.out.println("test низлево");
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("выход за пределы массива, но все ок");
        }
        try {
            if (field[x - 1][y] != CellState.EMPTY) {
                System.out.println("test левоцентр");
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("выход за пределы массива, но все ок");
        }

        System.out.println("test успешен");
        return true;
    }

    private void placeShip(int[] cellIndex, int shipSize, int shipForm) { //превращаем "относительный" корабль в "абсолютный"
        if (field[cellIndex[0]][cellIndex[1]] == CellState.EMPTY) {
            int[][] shipCells = new int[shipSize][2];
            for (int i = 0; i < shipSize; i++) {
                System.out.print("shipForm " + shipForm);
                switch (shipForm) {
                    case 0:
                        shipCells[i][0] = cellIndex[0] + i;
                        shipCells[i][1] = cellIndex[1];
                        break;
                    case 1:
                        shipCells[i][0] = cellIndex[0];
                        shipCells[i][1] = cellIndex[1] + i;
                        break;
                }
                System.out.print(shipCells[i][0] + " " + shipCells[i][1] + " ");
                field[shipCells[i][0]][shipCells[i][1]] = CellState.SHIP; //ставим корабль на поле

            }
            System.out.println("");
            Ship ship = new Ship(shipCells);
            ships.add(ship); //кэшируем корабль, чтобы не искать его каждый раз
        }
    }

    /*
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
    }*/
    public void getShoot(int[] cellIndex) {
        if (field[cellIndex[0]][cellIndex[1]] == CellState.SHIP) {
            field[cellIndex[0]][cellIndex[1]] = CellState.HIT;
            System.out.println("попал!");
        }
        if (field[cellIndex[0]][cellIndex[1]] == CellState.EMPTY) {
            field[cellIndex[0]][cellIndex[1]] = CellState.MISS;
            System.out.println("промазал!");
        }
    }

}
