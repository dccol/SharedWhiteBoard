package server;

import Shapes.*;

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

    // Users
    private ArrayList<String> users;

    public WhiteBoardAccess() {
        this.lines = new ArrayList<>();
        this.straightlines = new ArrayList<>();
        this.rectangles = new ArrayList<>();
        this.ovals = new ArrayList<>();
        this.circles = new ArrayList<>();
        this.text = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    // Operations on the whiteboard

    public ArrayList<String> getUsers(){
        return this.users;
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

    /** TO DO SYNCHRONOUS IN NEEDED **/
    public void addLine(FreeLine newLine){
        this.lines.add(newLine);
    }
    public void updateLine(int id, FreeLine line){
        this.lines.set(id, line);
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
    public int addUser(String user){
        if(users.contains(user)) {
            return 0;
        }
        this.users.add(user);
        return 1;
    }
    public int removeUser(String user){
        this.users.remove(user);
        return 1;
    }

}
