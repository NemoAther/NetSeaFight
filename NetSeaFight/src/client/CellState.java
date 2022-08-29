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
public enum CellState {
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
