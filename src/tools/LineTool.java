/*
* TCSS 305 – Autumn 2016
* Assignment 5 – PowerPaint
*/
package tools;

import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * Tool object that produces a line. Line's start point is where
 * line was created and end point is at coordinate where updateShape
 * last called (start point if shape never updated). 
 * @author James Roberts
 * @version 23 November 2016
 */
public class LineTool extends AbstractTool {
    /** X location of line's ending point. */
    private double myEndX;
    /** Y location of line's ending point. */
    private double myEndY;
    
    /** Constructor for the line tool. 
     * Line will be single pixel at (-100, -100) initially.
     */
    public LineTool() {
        super();
        myEndX = getStartingX();
        myEndY = getStartingY();
    }
    
    /**
     * {@inheritDoc}
     * Line's end point is set to the start location.
     */
    @Override
    public void createShape(final double theX, final double theY) {
        super.createShape(theX,  theY);
        myEndX = theX;
        myEndY = theY;
        
    }
    
    /**
     * {@inheritDoc}
     * Updates the end-point of the line to the passed coordinate.
     */
    @Override
    public void updateShape(final double theEndX, final double theEndY) {
        myEndX = theEndX;
        myEndY = theEndY;
    }
    
    /**
     * {@inheritDoc}
     * @return Line2D object representing the drawn line. 
     */
    @Override
    public Shape getShape() {
        return new Line2D.Double(getStartingX(), getStartingY(), myEndX, myEndY);
    }
    
    /**
     * {@inheritDoc}
     * Lines can never be filled.
     */
    @Override
    public boolean canFill() {
        return false;
    }

}
