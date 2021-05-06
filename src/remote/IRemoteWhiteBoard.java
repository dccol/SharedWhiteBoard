package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteWhiteBoard extends Remote {

    public int drawLine() throws RemoteException;

}
