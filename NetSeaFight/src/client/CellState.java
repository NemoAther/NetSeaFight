package client;

import java.io.Serializable;

/**
 *
 * @author GAV
 */
public enum CellState implements Serializable{
    //0 - пустая клетка
    //1 - корабль
    //2 - аура корабля (рисуется на пустых клетках после уничтожения корабля)
    //3 - промах
    //4 - попадание
    //5 - промах + аура (рисуется на промахах после уничтожения корабля)
    EMPTY,
    SHIP,
    SHIPAURA,
    MISS,
    HIT,
    MISSAURA
}
