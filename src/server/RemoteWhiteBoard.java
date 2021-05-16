package server;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Shapes.*;
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
    public ArrayList<FreeLine> getLines(){
        return whiteBoardAccess.getLines();
    }
    @Override
    public ArrayList<StraightLine> getStraightLines() throws RemoteException {
        return whiteBoardAccess.getStraightlines();
    }

    @Override
    public int addStraightLine(StraightLine straightLine) throws RemoteException {
        whiteBoardAccess.addStraightLine(straightLine);
        return 1;
    }

    @Override
    public ArrayList<Rectangle> getRectangles() throws RemoteException {
        return whiteBoardAccess.getRectangles();
    }
    @Override
    public ArrayList<Text> getText() throws RemoteException {
        return whiteBoardAccess.getText();
    }

    @Override
    public int addLine(FreeLine line) throws RemoteException {
        whiteBoardAccess.addLine(line);
        return whiteBoardAccess.getLines().size()-1;
    }

    @Override
    public int updateLine(int id, FreeLine line) throws RemoteException {
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
    public int addRectangle(Rectangle rectangle) throws RemoteException {
        whiteBoardAccess.addRectangle(rectangle);
        return 1;
    }

    @Override
    public int addTriangle() throws RemoteException {
        return 0;
    }


    @Override
    public int deleteLine() throws RemoteException {
        return 0;
    }

}
