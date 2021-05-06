package remote;

import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IRemoteWhiteBoard extends Remote {

    public int drawLine(ArrayList<Point> points) throws RemoteException;
    public int drawCircle() throws RemoteException;
    public int drawOval() throws RemoteException;
    public int drawRectangle() throws RemoteException;
    public int drawTriangle() throws RemoteException;

    public int deleteLine() throws RemoteException;

    public ArrayList<ArrayList<Point>> requestState() throws RemoteException;

}
