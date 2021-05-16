package client;

import Shapes.FreeLine;
import remote.IRemoteWhiteBoard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.*;


public class WhiteBoardFrame extends JFrame {

    private IRemoteWhiteBoard remoteWhiteBoard;

    private WhiteBoardPanel whiteBoardPanel;

    // Distributed by WhiteBoardAccess on line insertion
    int id = 0;

    // Allow client to select colour (buttonListener line.setColour())
    FreeLine line = new FreeLine(new ArrayList<Point>(), Color.black, 3, 0);

    public WhiteBoardFrame(IRemoteWhiteBoard remoteWhiteBoard){

        this.remoteWhiteBoard = remoteWhiteBoard;

        whiteBoardPanel = new WhiteBoardPanel(remoteWhiteBoard);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(whiteBoardPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        // Refresh Listener
        Timer timer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                whiteBoardPanel.repaint();
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();

        // Mouse Listeners
        whiteBoardPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                try {
                    // Store Points locally for real time updates
                    ArrayList<Point> points = line.getPoints();

                    // Add new point to line points
                    points.add(e.getPoint());

                    // Update the lines points locally
                    line.setPoints(points);
                    System.out.println(line.getPoints());

                    // Push update to WhiteBoardAccess
                    // If a new line, add
                    if (points.size() < 2) {
                        id = remoteWhiteBoard.addLine(line);
                        System.out.println("added new line");
                    }
                    // Otherwise update the existing line
                    else{
                        remoteWhiteBoard.updateLine(id, line);
                    }
                    // Refresh display as soon as it can
                    whiteBoardPanel.repaint();

                }catch(RemoteException ex){
                    System.out.println("Exception");
                }
            }

        });
        whiteBoardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e){

                // Reset new line
                line = new FreeLine(new ArrayList<Point>(), Color.black, 3, 0);
            }
        });

        // Button Listeners to select Colours

        // Button Listeners to select Shapes (Free Line, Straight Line, Circle, Oval, Rectangle, Square)

        // Pixel Size?
    }
}
