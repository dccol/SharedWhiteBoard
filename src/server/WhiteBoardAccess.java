package server;

import Shapes.*;
import client.Chat;
import client.User;

import java.awt.geom.*;
import java.util.ArrayList;

/**
 * Handles all operations on the WhiteBoard data structure
 */
public class WhiteBoardAccess {

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

    /** TO DO SYNCHRONOUS IN NEEDED **/
    public void addLine(FreeLine newLine){
        this.lines.add(newLine);
        System.out.println(lines);
        System.out.println(lines.size());
    }
    public void updateLine(int id, FreeLine line){
        this.lines.set(id, line);
        System.out.println("Sent: " + line.getPoints().size());
        System.out.println("Saved: " + lines.get(id).getPoints().size());
    }

    public void addStraightLine(StraightLine straightLine){
        this.straightlines.add(straightLine);
    }

    public void addRectangle(Rectangle rectangle){
        this.rectangles.add(rectangle);
    }

    public void addOval(Oval oval){
        this.ovals.add(oval);
    }

    public void addCircle(SerializableCircle circle){
        this.circles.add(circle);
    }

    public void addText(Text text){
        this.text.add(text);
    }

    // Optional addition
    public synchronized void deleteText(){

    }
    public int addUser(User user){
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

    public int updateUserStatus(User user){
        for(User user1 : users){
            if(user1.getUsername().equals(user.getUsername())){
                user1.setStatus(1);
            }
        }
        return 1;
    }

    public int removeUser(User user){
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

    public int addChat(Chat chat){
        this.chatBox.add(chat);
        return 1;
    }
}

