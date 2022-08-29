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

/**
 *
 * @author ksmnote
 */
public class GameScreen extends JPanel implements Runnable {

    final int gridSize = 10;
    final int cellSize = 30;
    //final int fieldSize = cellSize * 12;
    private final int CANVAS_WIDTH = 800;
    private final int CANVAS_HEIGHT = 600;
    private volatile Point cursor = new Point(0, 0);
    private final Collision collision;

    int whatDragged = 3; //0-ничего, 1 - одноклеточный, 2 - двухклеточный, 3 - трехклеточный, 4 - четрыехклеточный

    FightFieldGUI fightFieldGUI;
    FightFieldController fightFieldController;
    ShipHangar shipHangar;

    GameScreen() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        
        fightFieldController = new FightFieldController(gridSize);
        fightFieldGUI = new FightFieldGUI(this);

        collision = new Collision(cellSize, fightFieldGUI.getFieldSize(), fightFieldController);
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
                System.out.println("mouse pressed");
                collision.getCollision(cursor.getX(), cursor.getY(), whatDragged, 0); //форма пока всегда горизонтальная

                /*if (cursor.getX() < fightField.getFieldSize()) {
                    //работаем с полем 
                }
                if (cursor.getX() >= fightField.getFieldSize()) {
                    shipHangar.hangarOnClick();//работаем с ангаром
                }*/
            }
        });
    }

//почитать про буфферед имейдж
    void draw(Graphics g) {

        fightFieldGUI.draw(g);
        shipHangar.draw(g);        
        
        if (whatDragged > 0) {
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

    public int getWhatDragged() {
        return whatDragged;
    }
}
