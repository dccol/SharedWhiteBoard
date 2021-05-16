package Shapes;

import java.awt.*;
import java.awt.geom.Line2D;

public class StraightLine extends Shape{

    private Line2D line;
    private int isComplete = 1;

    public StraightLine(Line2D line, Color colour, int strokeSize, int fill){
        super(colour, strokeSize, fill);
        this.line = line;
    }
    public Line2D getLine() {
        return line;
    }

    public void setLine(Line2D line) {
        this.line = line;
    }

    public int getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
    }
}
