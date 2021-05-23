package client;

import Shapes.*;
import Shapes.Rectangle;
import remote.IRemoteWhiteBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.Shape;
import java.rmi.RemoteException;

/** Manage canvas draw operations */
public class WhiteBoardPanel extends JPanel {

    private IRemoteWhiteBoard remoteWhiteBoard;

    public WhiteBoardPanel(IRemoteWhiteBoard remoteWhiteBoard){

        this.setPreferredSize(new Dimension(1500,800));
        this.setBackground(Color.white);
        this.remoteWhiteBoard = remoteWhiteBoard;
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
            // Ovals
            for (Oval oval : remoteWhiteBoard.getOvals()) {
                g2d.setStroke(new BasicStroke(oval.getStrokeSize()));
                g2d.setPaint(oval.getColour());
                if(oval.getFill() == 1) {
                    g2d.fill(oval.getOval());
                }
                else {
                    g2d.draw(oval.getOval());
                }
            }
            // Circles
            for (SerializableCircle circle : remoteWhiteBoard.getCircles()) {
                g2d.setStroke(new BasicStroke(circle.getStrokeSize()));
                g2d.setPaint(circle.getColour());

                if(circle.getFill() == 1) {

                    drawCenteredCircleFill(g2d, (int) circle.getCentreX(), (int) circle.getCentreY(), (int) circle.getRadius());
                }
                else {
                    drawCenteredCircle(g2d, (int) circle.getCentreX(), (int) circle.getCentreY(), (int) circle.getRadius());
                }
            }
            // Text
            for (Text text : remoteWhiteBoard.getText()) {
                /** TO DO OPTIONAL: Font size, bold, underline **/
                g2d.drawString(text.getText(), text.getX(), text.getY());
            }

        }
        catch(RemoteException e){
            // If connection has been lost, the parent frame will handle.
            // Unable to throw since paintComponent is already defined
            //JOptionPane.showMessageDialog(null, "Error: Connection to the server has been lost.");
        }

    }
    public void drawCenteredCircleFill(Graphics2D g2d, int centreX, int centreY, int radius) {
        int diameter = 2*radius;
        g2d.fillOval(centreX - radius, centreY - radius, diameter, diameter);
    }
    public void drawCenteredCircle(Graphics2D g2d, int centreX, int centreY, int radius) {
        int diameter = 2*radius;
        g2d.drawOval(centreX - radius, centreY - radius, diameter, diameter);
    }

}
