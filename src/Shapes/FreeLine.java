package Shapes;

import java.awt.*;
import java.util.ArrayList;

public class FreeLine extends Shape {

    private ArrayList<Point> points;

    public FreeLine(ArrayList<Point> points, Color colour, int strokeSize, int fill) {
        super(colour, strokeSize, fill);
        this.points = points;
    }
    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }


}
