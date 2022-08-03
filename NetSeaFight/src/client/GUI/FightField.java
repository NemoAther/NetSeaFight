package client.GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author ksmnote
 */
public class FightField extends JPanel implements Runnable {

    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;
    Point cursor = new Point(0, 0);
    volatile int x1 = 0;
    volatile int y1 = 0;
    volatile int x2 = 10;
    volatile int y2 = 10;

    public FightField() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));        
        
        Thread redraw = new Thread(this);
        redraw.start();
    }
    
    @Override
    public void run() {
        int direction = 1;
        while (true) {
            repaint();
        }
    }

    void draw(Graphics g
    ) {
        g.setColor(Color.red);
        //g.drawLine(x1, y1, x2, y2);
        g.drawRect(x1, y1, x2, y2);
    }

    @Override
    public void paintComponent(Graphics g
    ) {
        super.paintComponent(g);  // paint background
        setBackground(Color.BLACK);
        draw(g);
    }

}
