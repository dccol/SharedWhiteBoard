package server;
import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Shapes.Line;
import remote.IRemoteWhiteBoard;

/**
 * Server side implementation of the remote interface.
 */
public class RemoteWhiteBoard extends UnicastRemoteObject implements IRemoteWhiteBoard {

    private WhiteBoardAccess whiteBoardAccess;

    protected RemoteWhiteBoard(WhiteBoardAccess whiteBoardAccess) throws RemoteException {
        this.whiteBoardAccess = whiteBoardAccess;
    }

    // Implement interface methods
    @Override
    public ArrayList<Line> getLines(){
        return whiteBoardAccess.getLines();
    }

    @Override
    public int addLine(Line line) throws RemoteException {
        whiteBoardAccess.addLine(line);
        System.out.println(whiteBoardAccess.getLines());
        System.out.println(whiteBoardAccess.getLines().size());

        return whiteBoardAccess.getLines().size()-1;
    }

    @Override
    public int updateLine(int id, Line line) throws RemoteException {
        whiteBoardAccess.updateLine(id, line);
        return 1;
    }

    @Override
    public int addCircle() throws RemoteException {
        return 0;
    }

    @Override
    public int addOval() throws RemoteException {
        return 0;
    }

    @Override
    public int addRectangle() throws RemoteException {
        return 0;
    }

    @Override
    public int addTriangle() throws RemoteException {
        return 0;
    }

    @Override
    public int deleteLine() throws RemoteException {
        return 0;
    }

    @Override
    public ArrayList<Line> requestState() throws RemoteException {
        return null;
    }
}
