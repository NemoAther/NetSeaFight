/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.GUI;

import java.awt.FlowLayout;
import javax.swing.JFrame;

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

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
