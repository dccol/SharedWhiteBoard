package client;

import javax.swing.*;
import java.awt.*;

public class WhiteBoardPanel extends JPanel {

    public WhiteBoardPanel(){

        this.setPreferredSize(new Dimension(500,500));
    }

    public void paint(Graphics g){

        // Cast to child Graphics2D
        Graphics2D g2d = (Graphics2D) g;

        // Set colour
        g2d.setPaint(Color.blue);

        // Set size
        g2d.setStroke(new BasicStroke(5));

        //g2d.fillRect(0,0, 100, 200);
        //g2d.drawLine(0,0,500,500);
        //g2d.drawRect(0,0, 100, 200);
        //g2d.drawOval(0,0, 100, 200);
        //g2d.drawArc(0,0, 100, 100, 0, 180);
//        int[] xPoints = {150,250,350};
//        int[] yPoints = {300,150,300};
//        g2d.drawPolygon(xPoints, yPoints, 3);
        g2d.setFont(new Font("Ink Free", Font.BOLD, 50));
        g2d.drawString("Hello World!", 50, 50);
    }
}
