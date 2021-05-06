package client;

import java.awt.*;
import javax.swing.*;


public class WhiteBoardFrame extends JFrame {

    private WhiteBoardPanel whiteBoardPanel;
    public WhiteBoardFrame(){

        whiteBoardPanel = new WhiteBoardPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(whiteBoardPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
