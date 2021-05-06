package server;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import remote.IRemoteWhiteBoard;

/**
 * Creates an instance of the RemoteWhiteBoard class and
 * publishes it in the rmiregistry
 */
public class RMIServer {

    public static void main(String[] args)  {

        try {
            IRemoteWhiteBoard remoteWhiteBoard = new RemoteWhiteBoard();

            //Publish the remote object's stub in the registry under the name "WhiteBoard"
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("WhiteBoard", remoteWhiteBoard);

            System.out.println("WhiteBoard server ready");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
