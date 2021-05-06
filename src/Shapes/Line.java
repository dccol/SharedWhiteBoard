package Shapes;

import java.awt.*;
import java.util.ArrayList;

public class Line extends Shape {

    private int strokeSize;
    public Line(ArrayList<Point> points, Color colour, int strokeSize) {
        super(points, colour);
        this.strokeSize = strokeSize;
    }
    public int getStrokeSize() {
        return strokeSize;
    }

    public void setStrokeSize(int strokeSize) {
        this.strokeSize = strokeSize;
    }

}
