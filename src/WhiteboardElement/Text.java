/** Author: Daniel Coleman 994887
 *  Date: 23/05/2021
 */

package WhiteboardElement;

import java.awt.*;

public class Text extends Shape{

    private int x;
    private int y;
    private String text;

    public Text(String text, int x, int y, Color colour, int strokeSize, int fill) {
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
