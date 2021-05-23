/** Author: Daniel Coleman 994887
 *  Date: 23/05/2021
 */

package WhiteboardElement;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Oval extends Shape{

    private Ellipse2D oval;
    private int isComplete = 1;

    public Oval(Ellipse2D oval, Color colour, int strokeSize, int fill) {
        super(colour, strokeSize, fill);
        this.oval = oval;
    }

    public Ellipse2D getOval() {
        return oval;
    }

    public void setOval(Ellipse2D oval) {
        this.oval = oval;
    }

    public int getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
    }
}
