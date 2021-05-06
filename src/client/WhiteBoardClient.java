package client;

import remote.IRemoteWhiteBoard;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class WhiteBoardClient {
    public static void main(String[] args) {

        try {
            //Connect to the rmiregistry that is running on localhost
            Registry registry = LocateRegistry.getRegistry("localhost");

            //Retrieve the stub/proxy for the remote math object from the registry
            IRemoteWhiteBoard remoteWhiteBoard = (IRemoteWhiteBoard) registry.lookup("WhiteBoard");

            // Launch GUI
            new WhiteBoardFrame(remoteWhiteBoard);

        }catch(Exception e) {
            e.printStackTrace();
        }

    }
}
