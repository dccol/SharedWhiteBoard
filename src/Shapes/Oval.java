package Shapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Oval extends Shape{

    private Ellipse2D oval;

    public Oval(Color colour, int strokeSize, int fill, Ellipse2D oval) {
        super(colour, strokeSize, fill);
        this.oval = oval;
    }

    public Ellipse2D getOval() {
        return oval;
    }

    public void setOval(Ellipse2D oval) {
        this.oval = oval;
    }
}
