package client.GUI;

import client.Client;
import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 *
 * @author GAV
 */
public class MainWindow extends JFrame {

    public void createGUI(Client client) {
        setLayout(new FlowLayout());
        GameScreen gameScreen = new GameScreen(client);
        add(gameScreen);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
