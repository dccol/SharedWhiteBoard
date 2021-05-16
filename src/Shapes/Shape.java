package Shapes;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Shape implements Serializable {

    private Color colour;
    private int strokeSize;
    private int fill;

    public Shape(Color colour, int strokeSize, int fill){
        this.colour = colour;
        this.strokeSize = strokeSize;
        this.fill = fill;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }
    public int getStrokeSize() {
        return strokeSize;
    }

    public void setStrokeSize(int strokeSize) {
        this.strokeSize = strokeSize;
    }

    public int getFill() {
        return fill;
    }

    public void setFill(int fill) {
        this.fill = fill;
    }
}
