package server;

import java.awt.*;
import java.util.ArrayList;

/**
 * Handles all operations on the WhiteBoard data structure
 */
public class WhiteBoardAccess {

    // WhiteBoard data structure
    private ArrayList<ArrayList<Point>> lines;

    public WhiteBoardAccess() {
        lines = new ArrayList<>();
    }

    // Operations on the whiteboard

    // When a user joins the system, retrieve the WhiteBoard state
    // Regularly update user state (event-based)
    public ArrayList<ArrayList<Point>> getPlaceholder(){
        return this.lines;
    }

    public synchronized void addLine(ArrayList<Point> points){
        this.lines.add(points);
        System.out.println(lines);
    }
    public synchronized void deleteShape(){

    }

    public synchronized void addText(){

    }
    public synchronized void deleteText(){

    }
}
