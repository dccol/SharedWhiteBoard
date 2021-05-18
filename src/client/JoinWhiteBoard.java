package client;

import remote.IRemoteWhiteBoard;

import javax.swing.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/** TO DO: Username, List of Users, Manager, **/
public class JoinWhiteBoard {
    public static void main(String[] args) {

        try {
            //Connect to the rmiregistry that is running on localhost
            Registry registry = LocateRegistry.getRegistry("localhost");

            //Retrieve the stub/proxy for the remote math object from the registry
            IRemoteWhiteBoard remoteWhiteBoard = (IRemoteWhiteBoard) registry.lookup("WhiteBoard");

            // Launch Login Dialog
            String username = JOptionPane.showInputDialog("Username");
            User newUser = new User(username, 0);
            // Check if user available
            int result = remoteWhiteBoard.addUser(newUser);
            while(result != 1){
                if(result == 0) {
                    newUser.setUsername(JOptionPane.showInputDialog("Username Taken. Enter a new Username"));
                }
                else if(result == 2){
                    newUser.setUsername(username = JOptionPane.showInputDialog("Invalid Username"));
                }
                result = remoteWhiteBoard.addUser(newUser);
            }
            // Wait for manager to accept user
            while(newUser.getStatus() != 1){
                newUser = remoteWhiteBoard.getUserByUsername(newUser);
            }
            // Launch GUI
            WhiteBoardFrame frame = new WhiteBoardFrame(remoteWhiteBoard, newUser);
            frame.run();


        }catch(Exception e) {
            e.printStackTrace();
        }

    }
}
