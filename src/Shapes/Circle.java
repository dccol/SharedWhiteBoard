package Shapes;

import java.awt.*;

public class Circle extends Shape{

    private javafx.scene.shape.Circle circle;

    public Circle(Color colour, int strokeSize, int fill, javafx.scene.shape.Circle circle ) {
        super(colour, strokeSize, fill);
        this.circle = circle;
    }

    public javafx.scene.shape.Circle getCircle() {
        return circle;
    }

    public void setCircle(javafx.scene.shape.Circle circle) {
        this.circle = circle;
    }
}
