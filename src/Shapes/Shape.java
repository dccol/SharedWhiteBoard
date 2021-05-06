package Shapes;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Shape implements Serializable {

    private ArrayList<Point> points;
    private Color colour;

    public Shape(ArrayList<Point> points, Color colour){
        this.points = points;
        this.colour = colour;
    }
    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }
}
