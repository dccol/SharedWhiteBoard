package server;
import java.awt.*;
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
    public int drawLine(Line line) throws RemoteException {
        whiteBoardAccess.addLine(line);
        return 1;
    }

    @Override
    public int drawCircle() throws RemoteException {
        return 0;
    }

    @Override
    public int drawOval() throws RemoteException {
        return 0;
    }

    @Override
    public int drawRectangle() throws RemoteException {
        return 0;
    }

    @Override
    public int drawTriangle() throws RemoteException {
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
