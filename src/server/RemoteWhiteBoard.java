package server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import remote.IRemoteWhiteBoard;

/**
 * Server side implementation of the remote interface.
 */
public class RemoteWhiteBoard extends UnicastRemoteObject implements IRemoteWhiteBoard {

    private int numberOfComputations;

    protected RemoteWhiteBoard() throws RemoteException {
        numberOfComputations = 0;
    }

    // Implement interface methods
    @Override
    public int drawLine() throws RemoteException {
        return 1;
    }
}
