package client;

/**
 * Класс расформирован и не используется
 * @author GAV
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

        }
    }

    public int[] getCellIndex(double cursorX, double cursorY) {
        int[] cellIndex = new int[2];

        cellIndex[0] = (int) (cursorX / cellSize);
        cellIndex[1] = (int) (cursorY / cellSize);
        return cellIndex;
    }
}
