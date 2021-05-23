/** Author: Daniel Coleman 994887
 *  Date: 23/05/2021
 */

package WhiteboardElement;

import java.awt.*;
import java.io.Serializable;

public class SerializableCircle extends Shape implements Serializable {

    private double centreX;
    private double centreY;
    private double radius;

    public SerializableCircle(double centreX, double centreY, double radius, Color colour, int strokeSize, int fill) {
        super(colour, strokeSize, fill);
        this.centreX = centreX;
        this.centreY = centreY;
        this.radius = radius;
    }

    public double getCentreX() {
        return centreX;
    }

    public void setCentreX(double centreX) {
        this.centreX = centreX;
    }

    public double getCentreY() {
        return centreY;
    }

    public void setCentreY(double centreY) {
        this.centreY = centreY;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
