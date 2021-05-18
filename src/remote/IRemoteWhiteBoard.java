package remote;

import Shapes.*;
import client.User;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IRemoteWhiteBoard extends Remote {

    // User
    public ArrayList<User> getUsers() throws RemoteException;
    public User getUserByUsername(User user) throws  RemoteException;
    public int addUser(User user) throws RemoteException;
    public int updateUserStatus(User user) throws RemoteException;
    public int removeUser(User user) throws RemoteException;

    // FreeLines
    public ArrayList<FreeLine> getLines() throws RemoteException;
    public int addLine(FreeLine line) throws RemoteException;
    public int updateLine(int id, FreeLine line) throws RemoteException;
    public int deleteLine() throws RemoteException;

    // Straight Line
    public ArrayList<StraightLine> getStraightLines() throws RemoteException;
    public int addStraightLine(StraightLine straightLineline) throws RemoteException;

    // Oval
    public ArrayList<Oval> getOvals() throws RemoteException;
    public int addOval(Oval oval) throws RemoteException;

    // Rectangle
    public ArrayList<Rectangle> getRectangles() throws RemoteException;
    public int addRectangle(Rectangle rectangle) throws RemoteException;

    // Circle
    public ArrayList<SerializableCircle> getCircles() throws RemoteException;
    public int addCircle(SerializableCircle circle) throws RemoteException;

    // Text
    public ArrayList<Text> getText() throws RemoteException;
    public int addText(Text text) throws RemoteException;

    // Triangle
    public int addTriangle() throws RemoteException;
}
