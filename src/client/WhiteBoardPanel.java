package client;

import Shapes.Line;
import remote.IRemoteWhiteBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class WhiteBoardPanel extends JPanel {

    private IRemoteWhiteBoard remoteWhiteBoard;
    //private ArrayList<Line> lines;

    public WhiteBoardPanel(IRemoteWhiteBoard remoteWhiteBoard){

        this.setPreferredSize(new Dimension(1000,500));
        this.remoteWhiteBoard = remoteWhiteBoard;
        //lines = new ArrayList<>();

    }
//    public ArrayList<Line> getLines(){
//        return this.lines;
//    }
//    public void setLines(ArrayList<Line> lines){
//        this.lines = lines;
//    }
//
//    public Line getLine(int index){
//        return this.lines.get(index);
//    }
//
//    public void setLine(Line line, int index){
//        this.lines.set(index, line);
//    }
//
//    public void addLine(Line line){
//        this.lines.add(line);
//    }

    // Draw WhiteBoardState
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw all components from shared data storage
        try {
            for (Line line : remoteWhiteBoard.getLines()) {
                g2d.setStroke(new BasicStroke(line.getStrokeSize()));
                g2d.setPaint(line.getColour());
                for (int i = 0; i < line.getPoints().size() - 2; i++) {
                    Point p1 = line.getPoints().get(i);
                    Point p2 = line.getPoints().get(i + 1);
                    g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
                }

            }
        }catch(RemoteException e){

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
