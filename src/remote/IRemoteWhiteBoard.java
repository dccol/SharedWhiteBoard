package remote;

import Shapes.Line;
import client.WhiteBoardPanel;
import server.WhiteBoardAccess;

import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IRemoteWhiteBoard extends Remote {

    // Lines
    public ArrayList<Line> getLines() throws RemoteException;
    public int addLine(Line line) throws RemoteException;
    public int updateLine(int id, Line line) throws RemoteException;
    public int deleteLine() throws RemoteException;

    // Circle
    public int addCircle() throws RemoteException;

    // Oval
    public int addOval() throws RemoteException;

    // Rectangle
    public int addRectangle() throws RemoteException;

    // Triangle
    public int addTriangle() throws RemoteException;

    public ArrayList<Line> requestState() throws RemoteException;

}
