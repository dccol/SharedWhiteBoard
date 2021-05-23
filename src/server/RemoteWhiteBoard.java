/** Author: Daniel Coleman 994887
 *  Date: 23/05/2021
 */

package server;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import WhiteboardElement.*;
import client.Chat;
import client.User;
import remote.IRemoteWhiteBoard;

/**
 * Server side implementation of the remote interface.
 */
public class RemoteWhiteBoard extends UnicastRemoteObject implements IRemoteWhiteBoard {

    private WhiteBoardAccess whiteBoardAccess;

    protected RemoteWhiteBoard(WhiteBoardAccess whiteBoardAccess) throws RemoteException {
        this.whiteBoardAccess = whiteBoardAccess;
    }

    @Override
    public ArrayList<User> getUsers() throws RemoteException {
        return whiteBoardAccess.getUsers();
    }

    @Override
    public User getUserByUsername(User user) throws RemoteException {
        return whiteBoardAccess.getUserByUsername(user);
    }

    @Override
    public int addUser(User user) throws RemoteException {
        return whiteBoardAccess.addUser(user);
    }

    @Override
    public int updateUserStatus(User user) throws RemoteException {
        return whiteBoardAccess.updateUserStatus(user);
    }
    @Override
    public int removeUser(User user) throws RemoteException {
        return whiteBoardAccess.removeUser(user);
    }

    // Implement interface methods
    @Override
    public ArrayList<FreeLine> getLines(){
        return whiteBoardAccess.getLines();
    }
    @Override
    public ArrayList<StraightLine> getStraightLines() throws RemoteException {
        return whiteBoardAccess.getStraightlines();
    }

    @Override
    public ArrayList<Rectangle> getRectangles() throws RemoteException {
        return whiteBoardAccess.getRectangles();
    }
    @Override
    public ArrayList<Oval> getOvals() throws RemoteException {
        return whiteBoardAccess.getOvals();
    }
    @Override
    public ArrayList<SerializableCircle> getCircles() throws RemoteException {
        return whiteBoardAccess.getCircles();
    }
    @Override
    public ArrayList<Text> getText() throws RemoteException {
        return whiteBoardAccess.getText();
    }

    @Override
    public ArrayList<Chat> getChat() throws RemoteException {
        return whiteBoardAccess.getChatBox();
    }
    @Override
    public int addLine(FreeLine line) throws RemoteException {
        whiteBoardAccess.addLine(line);
        return whiteBoardAccess.getLines().size()-1;
    }

    @Override
    public int updateLine(int id, FreeLine line) throws RemoteException {
        whiteBoardAccess.updateLine(id, line);
        return 1;
    }


    @Override
    public int addStraightLine(StraightLine straightLine) throws RemoteException {
        whiteBoardAccess.addStraightLine(straightLine);
        return 1;
    }
    @Override
    public int addRectangle(Rectangle rectangle) throws RemoteException {
        whiteBoardAccess.addRectangle(rectangle);
        return 1;
    }

    @Override
    public int addOval(Oval oval) throws RemoteException {
        whiteBoardAccess.addOval(oval);
        return 1;
    }

    @Override
    public int addCircle(SerializableCircle circle) throws RemoteException {
        whiteBoardAccess.addCircle(circle);
        return 1;
    }

    @Override
    public int addText(Text text) throws RemoteException {
        whiteBoardAccess.addText(text);
        return 1;
    }

    @Override
    public void clear() throws RemoteException {
        whiteBoardAccess.reset();
    }

    @Override
    public int deleteLine() throws RemoteException {
        return 0;
    }

    @Override
    public int addChat(Chat chat) throws RemoteException{
        return whiteBoardAccess.addChat(chat);
    }

    @Override
    public void saveAs(String path) throws RemoteException, IOException {
        whiteBoardAccess.serializeState(path);
    }
    @Override
    public void save() throws IOException {
        whiteBoardAccess.serializeState();
    }

    @Override
    public void load(String path) throws RemoteException, IOException, ClassNotFoundException {
        whiteBoardAccess.reset();
        whiteBoardAccess.deserializeState(path);
    }

    @Override
    public void newBoard() throws RemoteException{
        whiteBoardAccess.setFilepath(null);
        whiteBoardAccess.reset();
    }

}
