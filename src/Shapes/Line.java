package Shapes;

import java.awt.*;
import java.util.ArrayList;

public class Line extends Shape{

    private BasicStroke strokeSize;
    public Line(ArrayList<Point> points, Color colour, BasicStroke strokeSize) {
        super(points, colour);
        this.strokeSize = strokeSize;
    }
    public BasicStroke getStrokeSize() {
        return strokeSize;
    }

    public void setStrokeSize(BasicStroke strokeSize) {
        this.strokeSize = strokeSize;
    }

}
