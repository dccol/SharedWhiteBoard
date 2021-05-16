package Shapes;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Rectangle extends Shape{

    private Rectangle2D rectangle2D;

    public Rectangle(Color colour, int strokeSize, int fill) {
        super(colour, strokeSize, fill);
    }

    public Rectangle2D getRectangle2D() {
        return rectangle2D;
    }

    public void setRectangle2D(Rectangle2D rectangle2D) {
        this.rectangle2D = rectangle2D;
    }
}
