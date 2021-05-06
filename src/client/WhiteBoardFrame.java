package client;

import Shapes.Line;
import remote.IRemoteWhiteBoard;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.*;


public class WhiteBoardFrame extends JFrame {

    private IRemoteWhiteBoard remoteWhiteBoard;

    private WhiteBoardPanel whiteBoardPanel;

    // Handle index on state update, will become size()+1
    int index = 0;

    // Allow client to select colour (buttonListener line.setColour())
    Line line = new Line(new ArrayList<Point>(), Color.black, new BasicStroke(3));

    public WhiteBoardFrame(IRemoteWhiteBoard remoteWhiteBoard){

        this.remoteWhiteBoard = remoteWhiteBoard;

        whiteBoardPanel = new WhiteBoardPanel();

        // Mouse Listeners
        whiteBoardPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                // Store Points locally for real time updates
                ArrayList<Point> points = line.getPoints();

                // Add new point to line points
                points.add(e.getPoint());

                // Update the lines points
                line.setPoints(points);
                System.out.println(line.getPoints());

                // If a new line, add
                if(points.size() < 2){
                    whiteBoardPanel.addLine(line);
                    System.out.println("added new line");
                }
                // Update the line in the data structure
                whiteBoardPanel.setLine(line, index);

                // Refresh display as soon as it can
                whiteBoardPanel.repaint();
            }

        });
        whiteBoardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e){

//                // Retrieve Line from data structure
//                Line sendLine = whiteBoardPanel.getLine(index);
                // Send update to server
                try {
                    int result = remoteWhiteBoard.drawLine(line);
                    System.out.println(result);
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }

                line = new Line(new ArrayList<Point>(), Color.black, new BasicStroke(3));
                index ++;
            }
        });

        // Button Listeners to select Colours

        // Button Listeners to select Shapes (Free Line, Straight Line, Circle, Oval, Rectangle, Square)

        // Pixel Size?

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(whiteBoardPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
