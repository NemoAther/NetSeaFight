package client.GUI;

import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 *
 * @author GAV
 */
public class MainWindow extends JFrame {

    public MainWindow() {

    }

    public void createGUI() {
        setLayout(new FlowLayout());
        GameScreen gameScreen = new GameScreen();
        add(gameScreen);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
