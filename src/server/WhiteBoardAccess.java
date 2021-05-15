package server;

import Shapes.Line;

import java.awt.*;
import java.util.ArrayList;

/**
 * Handles all operations on the WhiteBoard data structure
 */
public class WhiteBoardAccess {

    // WhiteBoard data structure
    private ArrayList<Line> lines;

    public WhiteBoardAccess() {
        this.lines = new ArrayList<>();
    }

    // Operations on the whiteboard

    // When a user joins the system, retrieve the WhiteBoard state
    // Regularly update user state (event-based)
    public ArrayList<Line> getLines(){
        return this.lines;
    }

    public void addLine(Line newLine){
        this.lines.add(newLine);
    }
    public void updateLine(int id, Line line){
        this.lines.set(id, line);
    }
    public synchronized void deleteShape(){

    }

    public synchronized void addText(){

    }
    public synchronized void deleteText(){

    }
}
