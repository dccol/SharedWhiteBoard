package client;

import Shapes.FreeLine;
import Shapes.Rectangle;
import Shapes.StraightLine;
import Shapes.Text;
import remote.IRemoteWhiteBoard;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

public class WhiteBoardPanel extends JPanel {

    private IRemoteWhiteBoard remoteWhiteBoard;
    //private ArrayList<Line> lines;

    public WhiteBoardPanel(IRemoteWhiteBoard remoteWhiteBoard){

        this.setPreferredSize(new Dimension(1000,500));
        this.remoteWhiteBoard = remoteWhiteBoard;
        //lines = new ArrayList<>();

    }

    // Draw WhiteBoardState
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw all components from shared data storage
        try {
            // Free lines
            for (FreeLine line : remoteWhiteBoard.getLines()) {
                g2d.setStroke(new BasicStroke(line.getStrokeSize()));
                g2d.setPaint(line.getColour());
                for (int i = 0; i < line.getPoints().size() - 2; i++) {
                    Point p1 = line.getPoints().get(i);
                    Point p2 = line.getPoints().get(i + 1);
                    g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
                }

            }
            // Straight lines
            for (StraightLine line : remoteWhiteBoard.getStraightLines()) {
                g2d.setStroke(new BasicStroke(line.getStrokeSize()));
                g2d.setPaint(line.getColour());
                g2d.draw(line.getLine());
            }
            // Rectangles
            for (Rectangle rectangle : remoteWhiteBoard.getRectangles()) {
                g2d.setStroke(new BasicStroke(rectangle.getStrokeSize()));
                g2d.setPaint(rectangle.getColour());
                if(rectangle.getFill() == 1) {
                    g2d.fill(rectangle.getRectangle2D());
                }
                else {
                    g2d.draw(rectangle.getRectangle2D());
                }
            }
            // Circles

            // Text
            for (Text text : remoteWhiteBoard.getText()) {
                //g2d.setStroke(new BasicStroke(text.getStrokeSize()));
                //g2d.setPaint(text.getColour());
                g2d.drawString(text.getText(), text.getX(), text.getY());
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
