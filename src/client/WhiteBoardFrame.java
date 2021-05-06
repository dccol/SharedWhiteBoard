package client;

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
    ArrayList<Point> points = new ArrayList<>();

    public WhiteBoardFrame(IRemoteWhiteBoard remoteWhiteBoard){

        this.remoteWhiteBoard = remoteWhiteBoard;

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

                // Send update to server
                try {
                    int result = remoteWhiteBoard.drawLine(points);
                    System.out.println(result);
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
                points = new ArrayList<>();
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
