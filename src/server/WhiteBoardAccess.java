package server;

import java.util.ArrayList;

/**
 * Handles all operations on the WhiteBoard data structure
 */
public class WhiteBoardAccess {

    // WhiteBoard data structure
    private ArrayList<String> placeholder;

    public WhiteBoardAccess() {
        // initialise placeholder
    }

    // Operations on the whiteboard

    // When a user joins the system, retrieve the WhiteBoard state
    // Regularly update user state (event-based)
    public ArrayList<String> getPlaceholder(){
        return this.placeholder;
    }

    public synchronized void addShape(){

    }
    public synchronized void deleteShape(){

    }

    public synchronized void addText(){

    }
    public synchronized void deleteText(){

    }
}
