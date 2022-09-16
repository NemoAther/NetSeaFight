package client.GUI;

import client.Collision;
import client.FightFieldController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author GAV
 */
public class GameScreen extends JPanel implements Runnable {

    private final int gridSize = 10;
    private final int cellSize = 30;
    private final int CANVAS_WIDTH = 800;
    private final int CANVAS_HEIGHT = 600;
    private volatile Point cursor = new Point(0, 0);

    private int draggedSize = 0; //0-ничего, 1 - одноклеточный, 2 - двухклеточный, 3 - трехклеточный, 4 - четрыехклеточный
    private int draggedForm = 0; //0 - горизонтальный, 1 - вертикальный

    FightFieldGUI fightFieldGUI;
    FightFieldController fightFieldController;
    ShipHangar shipHangar;

    GameScreen() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        fightFieldController = new FightFieldController(gridSize);
        fightFieldGUI = new FightFieldGUI(this);

        shipHangar = new ShipHangar(this);

        addMouseMove();
        addMouseClick();

        Thread redraw = new Thread(this);
        redraw.start();
    }

    @Override
    public void run() {
        while (true) {
            repaint();
        }
    }

    private void addMouseMove() {
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                cursor = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }
        });
    }

    private void addMouseClick() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    if (draggedForm == 0) {
                        draggedForm = 1;
                    } else if (draggedForm == 1) {
                        draggedForm = 0;
                    }
                }
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (cursor.getX() < fightFieldGUI.getFieldSize() && cursor.getY() < fightFieldGUI.getFieldSize()) {
                        getCollision(cursor.getX(), cursor.getY(), draggedSize, draggedForm);
                    }
                    if (cursor.getX() >= fightFieldGUI.getFieldSize()) {
                        shipHangar.hangarOnClick(cursor.getX(), cursor.getY(), draggedSize, draggedForm);//работаем с ангаром
                    }
                }
            }
        });
    }

    public void getCollision(double cursorX, double cursorY, int sizeDragged, int formDragged) {
        int[] cellIndex = getCellIndex(cursorX, cursorY); //получаем координаты не в пикселях, а в ячейках
        if (draggedSize == 0) {
            //fightFieldController.getShoot(cellIndex);
            int removeResult = fightFieldController.removeShip(cellIndex);
            draggedSize = removeResult;
            draggedForm = 0;
        } else {
            boolean placeResult = fightFieldController.addShip(cellIndex, draggedSize, draggedForm);
            if (placeResult) {
                draggedSize = 0;
                draggedForm = 0;
            }
        }
    }

    public int[] getCellIndex(double cursorX, double cursorY) {
        int[] cellIndex = new int[2];
        cellIndex[0] = (int) (cursorX / cellSize);
        cellIndex[1] = (int) (cursorY / cellSize);
        return cellIndex;
    }

    void draw(Graphics g) {

        fightFieldGUI.draw(g, fightFieldController.getField());
        shipHangar.draw(g);

        if (draggedSize > 0) {
            shipHangar.drawShipOnCursor(g);
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // paint background
        setBackground(Color.BLACK);
        draw(g);
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getCellSize() {
        return cellSize;
    }

    public int getCursorX() {
        return (int) cursor.getX();
    }

    public int getCursorY() {
        return (int) cursor.getY();
    }

    public Point getPoint() {
        return cursor;
    }

    public int getDraggedSize() {
        return draggedSize;
    }

    public int getDraggedForm() {
        return draggedForm;
    }

    public void setDraggedSize(int draggedSize) {
        this.draggedSize = draggedSize;
    }

    public void setDraggedForm(int draggedForm) {
        this.draggedForm = draggedForm;
    }

}
