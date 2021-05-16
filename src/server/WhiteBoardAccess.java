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

    public WhiteBoardAccess() {
        this.lines = new ArrayList<>();
        this.straightlines = new ArrayList<>();
        this.rectangles = new ArrayList<>();
        this.ovals = new ArrayList<>();
        this.circles = new ArrayList<>();
        this.text = new ArrayList<>();
    }

    // Operations on the whiteboard

    // When a user joins the system, retrieve the WhiteBoard state
    // Regularly update user state (event-based)
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

    public synchronized void addText(){

    }

    // Optional addition
    public synchronized void deleteText(){

    }
}
