package client;

import Shapes.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class WhiteBoardPanel extends JPanel {

    private ArrayList<Line> lines;

    public WhiteBoardPanel(){

        this.setPreferredSize(new Dimension(500,500));
        lines = new ArrayList<>();

    }
    public ArrayList<Line> getLines(){
        return this.lines;
    }

    public Line getLine(int index){
        return this.lines.get(index);
    }

    public void setLine(Line line, int index){
        this.lines.set(index, line);
    }

    public void addLine(Line line){
        this.lines.add(line);
    }

    // Draw WhiteBoardState
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw every line in lines
        for (Line line: lines)
        {
            g2d.setStroke(line.getStrokeSize());
            g2d.setPaint(line.getColour());
            for(int i = 0; i < line.getPoints().size() - 2; i++) {
                Point p1 = line.getPoints().get(i);
                Point p2 = line.getPoints().get(i + 1);
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }

        }
    }

//    public void paint(Graphics g){
//
//        // Cast to child Graphics2D
//        Graphics2D g2d = (Graphics2D) g;
//
//        // Set colour
//        g2d.setPaint(Color.blue);
//
//        // Set size
//        g2d.setStroke(new BasicStroke(5));
//
//        //g2d.fillRect(0,0, 100, 200);
//        //g2d.drawLine(0,0,500,500);
//        //g2d.drawRect(0,0, 100, 200);
//        //g2d.drawOval(0,0, 100, 200);
//        //g2d.drawArc(0,0, 100, 100, 0, 180);
////        int[] xPoints = {150,250,350};
////        int[] yPoints = {300,150,300};
////        g2d.drawPolygon(xPoints, yPoints, 3);
//        //g2d.setFont(new Font("Ink Free", Font.BOLD, 50));
//        //g2d.drawString("Hello World!", 50, 50);
//    }
}
