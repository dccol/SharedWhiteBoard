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

            // Create WhiteBoardAccess object to handle concurrent access to shared resource
            WhiteBoardAccess whiteBoardAccess = new WhiteBoardAccess();

            IRemoteWhiteBoard remoteWhiteBoard = new RemoteWhiteBoard(whiteBoardAccess);

            //Publish the remote object's stub in the registry under the name "WhiteBoard"
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("WhiteBoard", remoteWhiteBoard);

            System.out.println("WhiteBoard server ready");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
