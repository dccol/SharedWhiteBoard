package server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
    public int drawLine() throws RemoteException {
        return 1;
    }
}
