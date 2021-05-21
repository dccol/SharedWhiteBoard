package server;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import client.User;
import client.WhiteBoardFrame;
import remote.IRemoteWhiteBoard;

import javax.swing.*;

/**
 * Creates an instance of the RemoteWhiteBoard class and
 * publishes it in the rmiregistry
 *  Launches manager GUI
 */
public class CreateWhiteBoard {

    private static String serverAddress;
    public static void main(String[] args)  {

        // Validate commandline args
        if(args.length < 1){
            System.out.println("Missing Arguments. Please Try Again");
            System.exit(0);

        }
        else if(args.length > 1){
            System.out.println("Too many Arguments. Please Try Again");
            System.exit(0);
        }

        serverAddress = args[0];

        try {

            // Create WhiteBoardAccess object to handle concurrent access to shared resource
            WhiteBoardAccess whiteBoardAccess = new WhiteBoardAccess();

            IRemoteWhiteBoard newRemoteWhiteBoard = new RemoteWhiteBoard(whiteBoardAccess);

            //Publish the remote object's stub in the registry under the name "WhiteBoard"
            Registry registry = LocateRegistry.getRegistry(serverAddress);
            registry.bind("WhiteBoard", newRemoteWhiteBoard);

            IRemoteWhiteBoard remoteWhiteBoard = (IRemoteWhiteBoard) registry.lookup("WhiteBoard");

            System.out.println("WhiteBoard server ready");

            // Launch Login Dialog
            String username = JOptionPane.showInputDialog("Username");
            User manager = new User(username, 1);

            int result = remoteWhiteBoard.addUser(manager);

            // Launch GUI
            ManagerWhiteBoardFrame frame = new ManagerWhiteBoardFrame(remoteWhiteBoard, manager);
            frame.run();

        } catch (Exception e) {
            System.out.println("Error: Unable to connect to rmiregistry");
        }

    }
}
