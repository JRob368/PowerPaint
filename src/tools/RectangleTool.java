/*
* TCSS 305 – Autumn 2016
* Assignment 5 – PowerPaint
*/
package tools;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
/**
 * An object representing a tool that can draw Rectangles. One corner of the
 * Rectangles's bounding box is anchored to the point used with the opposite
 * corner being located at the coordinate where the shape was last updated.
 * @author James Roberts
 * @version 23 November 2016
 */
public class RectangleTool extends AbstractTool {
    /** The width of the rectangle. */
    private double myWidth;
    /** The height of the rectangle. */
    private double myHeight;
    /** The x location of the rectangle. */
    private double myCurX;
    /** The Y location of the rectangle. */
    private double myCurY;
    
    /** Constructs a RectangleTool object at location (-100, -100)
     * with a width and height of 0.
     */
    public RectangleTool() {
        super();
        myCurX = getStartingX();
        myCurY = getStartingY();
        myWidth = 0;
        myHeight = 0;
    }
    
    /**
     * Creates the rectangular object with it's upper right corner at the
     * passed x and y and with a width and height of 0. 
     */
    @Override
    public void createShape(final double theX, final double theY) {
        super.createShape(theX, theY);
        myCurX = theX;
        myCurY = theY;
        myWidth = 0;
        myHeight = 0;
          
    }
    
    /**
     * Updates the rectangle based on the passed coordinate. Rectangle will have one corner
     * anchored to the point it was created on with the opposite corner ending at the 
     * passed point.
     * @param theEndX x coordinate of opposite corner.
     * @param theEndY y coordinate of opposite corner.
     */
    @Override
    public void updateShape(final double theEndX, final double theEndY) {
        updateWidth(theEndX);
        updateHeight(theEndY);
    }
    
    /**
     * Updates the Rectangle's width, changing it's x coordinate if necessary.
     * @param theX An X coordinate.
     */
    protected void updateWidth(final double theX) {
        if (theX >= getStartingX()) { //x coordinate to right of start point
            myCurX = getStartingX();
            myWidth  = theX - getStartingX();
        } else { //x coordinate to left, must update rectangle's coordinate
            myCurX = theX;
            myWidth = getStartingX() - theX;  
        }
    }
    
    /**
     * Updates the Rectangle's height, changing it's y coordinate if necessary.
     * @param theY a Y coordinate.
     */
    protected void updateHeight(final double theY) {
        if (theY >= getStartingY()) { //y coordinate below start point
            myCurY = getStartingY();
            myHeight = theY - getStartingY();
        } else { //y coordinate above, must update rectangle's coordinate
            myCurY = theY;
            myHeight = getStartingY() - theY;
        }
    }
    
    /**
     * Returns the Shape's height.
     * @return The height.
     */
    protected double getHeight() {
        return myHeight;
    }
    
    /**
     * Returns the Shape's width.
     * @return The width.
     */
    protected double getWidth() {
        return myWidth;
    }
    
    /**
     * Returns the current x position of the rectangle's coordinate.
     * @return Rectangle's x coordinate.
     */
    protected double getCurX() {
        return myCurX;
    }
    
    /**
     * Returns the current y position of the rectangle's coordinate.
     * @return Rectangle's y coordinate.
     */
    protected double getCurY() {
        return myCurY;
    }
    
    /**
     * Returns a rectangle with one corner of it's bounding box at the start point 
     * with the location of it's other corner at the point where updateShape was
     * last called (start point if updateShape never called).
     */
    @Override 
    public Shape getShape() {
        return new Rectangle2D.Double(myCurX, myCurY, myWidth, myHeight);
    }
    
    /**
     * {@inheritDoc}
     * Rectangular shapes can be filled.
     */
    @Override
    public boolean canFill() {
        return true;
    }

}
