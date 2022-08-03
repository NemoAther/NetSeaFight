/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.GUI;

import java.awt.FlowLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author ksmnote
 */
public class MainWindow extends JFrame {

    public MainWindow() {

    }

    public void createGUI() {
        setLayout(new FlowLayout());
        FightField fightField = new FightField();
        add(fightField);
        
        fightField.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                fightField.cursor = e.getLocationOnScreen();
                fightField.x1 = e.getX();
                fightField.y1 = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
           
            }
        });
        
        
        
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
