/*
* TCSS 305 – Autumn 2016
* Assignment 5 – PowerPaint
*/
package tools;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * An object representing a tool that can draw Ellipses. One corner of the
 * Ellipse's bounding box is anchored to the point used with the opposite
 * corner being located at the coordinate where the shape was last updated. 
 * @author James Roberts
 * @version 23 November 2016
 */
public class EllipseTool extends RectangleTool {
    
    /**
     * Constructor for the EllipseTool.
     */
    public EllipseTool() {
        super();
    }
    
    /**
     * Returns an ellipse with one corner of it's bounding box at the start point 
     * with the location of it's other corner at the point where updateShape was
     * last called (start point if updateShape never called).
     */
    @Override
    public Shape getShape() {
        return new Ellipse2D.Double(getCurX(), getCurY(), getWidth(), getHeight());
    }

}
