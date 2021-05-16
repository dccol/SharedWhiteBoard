package client;

import remote.IRemoteWhiteBoard;

import javax.swing.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/** TO DO: Username, List of Users, Manager, **/
public class WhiteBoardClient {
    public static void main(String[] args) {

        try {
            //Connect to the rmiregistry that is running on localhost
            Registry registry = LocateRegistry.getRegistry("localhost");

            //Retrieve the stub/proxy for the remote math object from the registry
            IRemoteWhiteBoard remoteWhiteBoard = (IRemoteWhiteBoard) registry.lookup("WhiteBoard");

            // Launch Login Dialog
            String username = JOptionPane.showInputDialog("Username");

            // Check if user available
            int result = remoteWhiteBoard.addUser(username);
            while(result != 1){
                if(result == 0) {
                    username = JOptionPane.showInputDialog("Username Taken. Enter a new Username");
                }
                else if(result == 2){
                    username = JOptionPane.showInputDialog("Invalid Username");
                }
                result = remoteWhiteBoard.addUser(username);
            }
            // Launch GUI
            WhiteBoardFrame frame = new WhiteBoardFrame(remoteWhiteBoard, username);
            frame.run();


        }catch(Exception e) {
            e.printStackTrace();
        }

    }
}
