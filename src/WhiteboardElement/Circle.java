/** Author: Daniel Coleman 994887
 *  Date: 23/05/2021
 */

package WhiteboardElement;

import java.awt.*;

public class Circle extends Shape{

    private javafx.scene.shape.Circle circle;
    private int isComplete = 1;

    public Circle(javafx.scene.shape.Circle circle, Color colour, int strokeSize, int fill) {
        super(colour, strokeSize, fill);
        this.circle = circle;
    }

    public javafx.scene.shape.Circle getCircle() {
        return circle;
    }

    public void setCircle(javafx.scene.shape.Circle circle) {
        this.circle = circle;
    }

    public int getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
    }
}
