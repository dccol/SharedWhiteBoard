/** Author: Daniel Coleman 994887
 *  Date: 23/05/2021
 */

package WhiteboardElement;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shape{

//    private static final long serialVersionUID = 6529685098267757690L;
    private Rectangle2D rectangle2D;
    private int isComplete = 1;

    public Rectangle(Rectangle2D rectangle2D, Color colour, int strokeSize, int fill) {
        super(colour, strokeSize, fill);
        this.rectangle2D = rectangle2D;
    }

    public Rectangle2D getRectangle2D() {
        return rectangle2D;
    }

    public void setRectangle2D(Rectangle2D rectangle2D) {
        this.rectangle2D = rectangle2D;
    }

    public int getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
    }
}
