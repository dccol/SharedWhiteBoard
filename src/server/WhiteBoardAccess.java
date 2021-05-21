package server;

import Shapes.*;
import client.Chat;
import client.User;

import java.awt.geom.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Handles all operations on the WhiteBoard data structure
 */
public class WhiteBoardAccess {

    // Filename
    private String filepath;
    // WhiteBoard data structure
    private ArrayList<FreeLine> lines;
    private ArrayList<StraightLine> straightlines;
    private ArrayList<Rectangle> rectangles;
    private ArrayList<Oval> ovals;
    private ArrayList<SerializableCircle> circles;
    private ArrayList<Text> text;

    // Chatbox
    private ArrayList<Chat> chatBox;

    // Users
    private ArrayList<User> users;

    public WhiteBoardAccess() {
        this.lines = new ArrayList<>();
        this.straightlines = new ArrayList<>();
        this.rectangles = new ArrayList<>();
        this.ovals = new ArrayList<>();
        this.circles = new ArrayList<>();
        this.text = new ArrayList<>();
        this.users = new ArrayList<>();
        this.chatBox = new ArrayList<>();

        this.filepath = null;
    }

    // Operations on the whiteboard
    public ArrayList<User> getUsers(){
        return this.users;
    }

    public User getUserByUsername(User user){
        return this.users.stream().filter(elem -> elem.getUsername().equals(user.getUsername())).findFirst().orElse(null);
    }
    public ArrayList<FreeLine> getLines(){
        return this.lines;
    }

    public ArrayList<StraightLine> getStraightlines() {
        return straightlines;
    }

    public ArrayList<Rectangle> getRectangles() {
        return rectangles;
    }

    public ArrayList<Oval> getOvals() {
        return ovals;
    }

    public ArrayList<SerializableCircle> getCircles(){
        return circles;
    }

    public ArrayList<Text> getText() {
        return text;
    }

    public ArrayList<Chat> getChatBox(){
        return chatBox;
    }

    /** SYNCHRONOUS IF ALL USERS CAN PERFORM OPERATION, AND OPERATION CHANGES STATE **/
    public synchronized void addLine(FreeLine newLine){
        this.lines.add(newLine);
    }
    public synchronized void updateLine(int id, FreeLine line){
        this.lines.set(id, line);
    }

    public synchronized void addStraightLine(StraightLine straightLine){
        this.straightlines.add(straightLine);
    }

    public synchronized void addRectangle(Rectangle rectangle){
        this.rectangles.add(rectangle);
    }

    public synchronized void addOval(Oval oval){
        this.ovals.add(oval);
    }

    public synchronized void addCircle(SerializableCircle circle){
        this.circles.add(circle);
    }

    public synchronized void addText(Text text){
        this.text.add(text);
    }

    public synchronized int addUser(User user){
        if(users.contains(user)) {
            return 0;
        }
        else if(user.getUsername() == null){
            return 2;
        }
        else if(user.getUsername().isEmpty()){
            return 2;
        }
        this.users.add(user);
        return 1;
    }

    public synchronized int updateUserStatus(User user){
        for(User user1 : users){
            if(user1.getUsername().equals(user.getUsername())){
                user1.setStatus(1);
            }
        }
        return 1;
    }

    public synchronized int removeUser(User user){
        users.removeIf(user1 -> user1.getUsername().equals(user.getUsername()));
        return 1;
    }

    public void reset(){
        this.lines = new ArrayList<>();
        this.straightlines = new ArrayList<>();
        this.rectangles = new ArrayList<>();
        this.ovals = new ArrayList<>();
        this.circles = new ArrayList<>();
        this.text = new ArrayList<>();
    }

    public synchronized int addChat(Chat chat){
        this.chatBox.add(chat);
        return 1;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /** Serialize State **/
    /** TO DO: Serialise all shapes not just lines **/
    public void serializeState(String path) throws IOException {

        FileOutputStream fileOut = new FileOutputStream(path + ".ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(lines);
        out.flush();

        out.close();
        fileOut.close();
        System.out.println("Serialized data is saved in " + path);
        filepath = path;

    }
    public void serializeState() throws IOException{

        FileOutputStream fileOut = new FileOutputStream(filepath);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(lines);
        out.flush();
        out.close();
        fileOut.close();
        System.out.println("Serialized data is saved in " + filepath);

    }

    public void deserializeState(String path) throws IOException, ClassNotFoundException{
        Object o = null;

        FileInputStream fileIn = new FileInputStream(path);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        o =  in.readObject();
        in.close();
        fileIn.close();

        System.out.println(o);
        ArrayList<FreeLine> lines = (ArrayList<FreeLine>) o;
        System.out.println(lines);
        this.lines = lines;
        this.filepath = path;
    }
}

