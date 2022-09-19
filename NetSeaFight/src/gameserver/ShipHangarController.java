package gameserver;

/**
 *
 * @author GAV
 */
public class ShipHangarController {

    private int fourCellShip = 1;
    private int threeCellShip = 2;
    private int twoCellShip = 3;
    private int oneCellShip = 4;

    public boolean getShip(int shipSize) {
        switch (shipSize) {
            case 1:
                if (oneCellShip > 0) {
                    oneCellShip--;
                    return true;
                } else {
                    return false;
                }
            case 2:
                if (twoCellShip > 0) {
                    twoCellShip--;
                    return true;
                } else {
                    return false;
                }
            case 3:
                if (threeCellShip > 0) {
                    threeCellShip--;
                    return true;
                } else {
                    return false;
                }
            case 4:
                if (fourCellShip > 0) {
                    fourCellShip--;
                    return true;
                } else {
                    return false;
                }
        }
        return false;
    }

    public void returnShip(int shipSize) {
        switch (shipSize) {
            case 1:
                oneCellShip++;
                break;
            case 2:
                twoCellShip++;
                break;
            case 3:
                threeCellShip++;
                break;
            case 4:
                fourCellShip++;
                break;
        }
    }

    public boolean getShipLast(int shipSize) {
        switch (shipSize) {
            case 1:
                if (oneCellShip > 0) {
                    return true;
                }
                break;
            case 2:
                if (twoCellShip > 0) {
                    return true;
                }
                break;
            case 3:
                if (threeCellShip > 0) {
                    return true;
                }
                break;
            case 4:
                if (fourCellShip > 0) {
                    return true;
                }
                break;
        }
        return false;
    }

    boolean isHangarEmpty() {
        if (0== fourCellShip + threeCellShip + twoCellShip + oneCellShip) {
            return true;
        }
        return false;
    }
}
