package client;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;


public class WhiteBoardFrame extends JFrame {

    private WhiteBoardPanel whiteBoardPanel;
    int index = 0;
    ArrayList<Point> points = new ArrayList<>();

    public WhiteBoardFrame(){

        whiteBoardPanel = new WhiteBoardPanel();



        whiteBoardPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                points.add(e.getPoint());

                if(points.size() < 2){
                    whiteBoardPanel.addLine(points);
                }


                whiteBoardPanel.getLinePoints(index);
                whiteBoardPanel.setLinePoints(points, index);


                whiteBoardPanel.repaint(); //request Swing to refresh display as soon as it can
            }

        });
        whiteBoardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e){
                points = new ArrayList<>();
                index ++;
                System.out.println(whiteBoardPanel.getLines());
            }
        });
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(whiteBoardPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
