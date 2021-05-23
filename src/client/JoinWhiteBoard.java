/** Author: Daniel Coleman 994887
 *  Date: 23/05/2021
 */

package client;

import remote.IRemoteWhiteBoard;

import javax.swing.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/** Launch Client GUI */
public class JoinWhiteBoard {

    private static String serverAddress;
    public static void main(String[] args) {

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
            //Connect to the rmiregistry that is running on localhost
            Registry registry = LocateRegistry.getRegistry(serverAddress);

            //Retrieve the stub/proxy for the remote math object from the registry
            IRemoteWhiteBoard remoteWhiteBoard = (IRemoteWhiteBoard) registry.lookup("WhiteBoard");

            // Launch Login Dialog
            String username = JOptionPane.showInputDialog("Username");

            // If the user does not enter a username exit
            if(username == null){
                System.exit(0);
            }
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
                if(newUser == null){
                    // User has been rejected, exit
                    JOptionPane.showMessageDialog(null, "Sorry the host has rejected your request to join. :(");
                    System.exit(0);
                }
            }
            // Launch GUI
            WhiteBoardFrame frame = new WhiteBoardFrame(remoteWhiteBoard, newUser);
            frame.run();


        }catch(Exception e) {
            System.out.println("Error: Unable to connect to RMI server");
        }

    }
}
