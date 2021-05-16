package Shapes;

import java.awt.*;

public class Text extends Shape{

    private int x;
    private int y;
    private String text;

    public Text(Color colour, int strokeSize, int fill, String text, int x, int y) {
        super(colour, strokeSize, fill);
        this.text = text;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
