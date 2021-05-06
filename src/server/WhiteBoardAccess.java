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
        lines = new ArrayList<>();
    }

    // Operations on the whiteboard

    // When a user joins the system, retrieve the WhiteBoard state
    // Regularly update user state (event-based)
    public ArrayList<Line> getLines(){
        return this.lines;
    }

    public synchronized void addLine(Line newLine){
        this.lines.add(newLine);
        for(Line line : lines){
            System.out.println(line.getPoints().size());
        }
    }
    public synchronized void deleteShape(){

    }

    public synchronized void addText(){

    }
    public synchronized void deleteText(){

    }
}
