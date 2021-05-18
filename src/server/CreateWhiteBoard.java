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
 */
public class CreateWhiteBoard {

    public static void main(String[] args)  {

        try {

            // Create WhiteBoardAccess object to handle concurrent access to shared resource
            WhiteBoardAccess whiteBoardAccess = new WhiteBoardAccess();

            IRemoteWhiteBoard remoteWhiteBoard = new RemoteWhiteBoard(whiteBoardAccess);

            //Publish the remote object's stub in the registry under the name "WhiteBoard"
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("WhiteBoard", remoteWhiteBoard);

            System.out.println("WhiteBoard server ready");
            // Launch Login Dialog
            String username = JOptionPane.showInputDialog("Username");
            User manager = new User(username, 1);

            // Check if user available
            int result = remoteWhiteBoard.addUser(manager);
//            while(result != 1){
//                if(result == 0) {
//                    manager.setUsername(JOptionPane.showInputDialog("Username Taken. Enter a new Username"));
//                }
//                else if(result == 2){
//                    manager.setUsername(username = JOptionPane.showInputDialog("Invalid Username"));
//                }
//                result = remoteWhiteBoard.addUser(manager);
//            }
            // Launch GUI
            WhiteBoardFrame frame = new WhiteBoardFrame(remoteWhiteBoard, manager);
            frame.run();

        } catch (Exception e) {
            System.out.println("RMI not started");
        }

    }
}
