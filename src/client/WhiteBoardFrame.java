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
    int index = 0;
    Line line = new Line(new ArrayList<Point>(), Color.black);

    public WhiteBoardFrame(IRemoteWhiteBoard remoteWhiteBoard){

        this.remoteWhiteBoard = remoteWhiteBoard;

        whiteBoardPanel = new WhiteBoardPanel();

        whiteBoardPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

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

                line = new Line(new ArrayList<Point>(), Color.black);
                index ++;
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(whiteBoardPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
